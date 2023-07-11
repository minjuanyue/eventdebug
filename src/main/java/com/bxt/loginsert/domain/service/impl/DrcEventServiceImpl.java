package com.bxt.loginsert.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bxt.loginsert.common.TrafficEnum;
import com.bxt.loginsert.domain.po.*;
import com.bxt.loginsert.domain.repository.*;
import com.bxt.loginsert.domain.service.DrcEventService;
import com.google.protobuf.InvalidProtocolBufferException;
import if_drc_crm.IfDrcCrmR4;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class DrcEventServiceImpl implements DrcEventService {

    private final static Set<String> operationSet = new HashSet<>();

    static {
        operationSet.add("add");
        operationSet.add("update");
    }

    @Resource
    private TrafficEventMapper trafficEventMapper;
    @Resource
    private TrafficEventHistoryMapper trafficEventHistoryMapper;
    @Resource
    private TrafficEventObjectMapper trafficEventObjectMapper;
    @Resource
    private TrafficEventPilelocMapper trafficEventPilelocMapper;
    @Resource
    private TrafficEventGpsLocMapper trafficEventGpsLocMapper;
    @Resource
    private TrafficEventUtmLocMapper trafficEventUtmLocMapper;
    @Resource
    private TrafficEventJudgeTimeMapper trafficEventJudgeTimeMapper;
    @Resource
    private TrafficEventImageMapper trafficEventImageMapper;
    @Resource
    private TrafficEventLaneMapper trafficEventLaneMapper;

    @Override
    public void saveOrUpdateTrafficEvent(byte[] msg) throws InvalidProtocolBufferException {

        TrafficEventPO param = getTrafficEventPO(msg);

        String operation = param.getOperation();

        long startTime = System.currentTimeMillis();
        if (!operationSet.contains(operation)) {
            log.error("operation type is error");
            return;
        }

        TrafficEventPO oldOne = trafficEventMapper.findSignEvent(param.getDrcEventReportId(), null);

        if (oldOne == null) {
            // 兼容maps
            if (TrafficEnum.RelievedEnum.YES.getCode().equals(param.getIsRelieved())) {
                operation = "delete";
            } else {
                operation = "add";
            }

            param.setCheckStatus(TrafficEnum.CheckedEnum.NO.getCode());
            param.setOccurTime(findOccurTime(param.getJudgeTimes()));
            param.setSectionId(findSectionIdByHdLaneIds(param));
            trafficEventMapper.insertOne(param);
            insertTrafficEventRelevance(param);
            log.info("operation insert is success, operation:{}, trafficEventId:{}, drcEventReportId:{}",
                    operation, param.getTrafficEventId(), param.getDrcEventReportId());
        } else {
            // 兼容maps
            param.setOccurTime(oldOne.getOccurTime());
            if (TrafficEnum.RelievedEnum.YES.getCode().equals(param.getIsRelieved())) {
                operation = "delete";
                param.setHdLaneIds(oldOne.getHdLaneIds());
                param.setTrafficEventType(oldOne.getTrafficEventType());
                param.setForbiddenZoneId(oldOne.getForbiddenZoneId());
            } else {
                operation = "update";
            }
            param.setSectionId(findSectionIdByHdLaneIds(param));

            if (param.getTrafficEventId() == null) {
                param.setTrafficEventId(oldOne.getTrafficEventId());
                param.setCheckStatus(oldOne.getCheckStatus());
            }

            if (TrafficEnum.RelievedEnum.YES.getCode().equals(oldOne.getIsRelieved())) {
                if (TrafficEnum.RelievedEnum.YES.getCode().equals(param.getIsRelieved())) {
                    // 多次解除直接丢弃
                    log.info("trafficEvent 多次解除--存库耗时:{},  drcEventReportId:{}", System.currentTimeMillis() - startTime, param.getDrcEventReportId());
                    return;
                }

                // 备份解除之后的事件信息
                if (!CollectionUtils.isEmpty(param.getTrafficEventImageUrls()) && "update".equals(operation) && param.getDrcEventReportTime() == null) {
                    param = updateRedisAndInsertTrafficEventImage(param);
                } else {
                    insertTrafficEventRelevance(param);
                }
                // 解除事件不处理
                log.info("trafficEvent 解除事件（新增，更新）--存库耗时:{},  drcEventReportId:{}", System.currentTimeMillis() - startTime, param.getDrcEventReportId());
                return;
            }

            if (!CollectionUtils.isEmpty(param.getTrafficEventImageUrls()) && "update".equals(operation) && param.getDrcEventReportTime() == null) {
                param = updateRedisAndInsertTrafficEventImage(param);
            } else {
                // 更新
                trafficEventMapper.update(param);

                insertTrafficEventRelevance(param);
            }
            log.info("operation update is success, operation:{}, trafficEventId:{}, drcEventReportId:{}",
                    operation, param.getTrafficEventId(), param.getDrcEventReportId());
        }

        log.info("trafficEvent 正常存库耗时:{}", System.currentTimeMillis() - startTime);
    }

    public TrafficEventPO getTrafficEventPO(byte[] content) throws InvalidProtocolBufferException {
        IfDrcCrmR4.DRC_CRM_EVENT_IND trafficEvent = IfDrcCrmR4.DRC_CRM_EVENT_IND.parseFrom(content);

        String operation = null;
        Date now = new Date();

        TrafficEventPO vo = new TrafficEventPO();
        if (trafficEvent.hasDrcId()) {
            vo.setDrcId((long) trafficEvent.getDrcId());
        }
        vo.setTrafficEventType(trafficEvent.hasEventType() ? trafficEvent.getEventType().getNumber() : null);
        if (trafficEvent.getJudgeTimesCount() > 0) {
//            vo.setJudgeTimes(new ArrayList<Date>() {{
//                trafficEvent.getJudgeTimesList().forEach(timestamp -> add(new Date(timestamp)));
//            }});
            // TODO
            vo.setJudgeTimes(new ArrayList<JudgeTimePO>() {{
                    trafficEvent.getJudgeTimesList().forEach(timestamp -> add(new JudgeTimePO(new Date(timestamp))));
            }});
        }
        if (trafficEvent.hasDirection()) {
            vo.setDirectionIndication(trafficEvent.getDirection());
        }
        if (trafficEvent.hasEventState()) {
            vo.setIsRelieved(trafficEvent.getEventState() == 2 ? 1 : 0);
            if (trafficEvent.getEventState() == 0) {
                operation = "add";
            } else {
                operation = "update";
            }
        }
        if (trafficEvent.getEventState() == 2) {
            vo.setRelieveTime(trafficEvent.hasReportTime() ? new Date(trafficEvent.getReportTime()) : now);
        }
        if (trafficEvent.hasEventID()) {
            vo.setDrcEventReportId(trafficEvent.getEventID());
        }
        vo.setIsMock(0);
        if (trafficEvent.getHdLanesCount() > 0) {
            vo.setHdLaneIds(StringUtils.join(trafficEvent.getHdLanesList(), ","));
        }
        if (trafficEvent.getHdConnectionsCount() > 0) {
            vo.setHdConnectionIds(StringUtils.join(trafficEvent.getHdConnectionsList(), ","));
        }
        if (trafficEvent.getUtmLocsCount() > 0) {
            vo.setUtmLoc(new ArrayList<UtmLocPO>() {{
                trafficEvent.getUtmLocsList().forEach(utmLoc -> {
                    UtmLocPO utmLoc1 = new UtmLocPO();
                    utmLoc1.setUtmX(utmLoc.getUtmX());
                    utmLoc1.setUtmY(utmLoc.getUtmY());
                    utmLoc1.setUtmZ(utmLoc.getUtmZ());
                    add(utmLoc1);
                });
            }});
        }
        if (trafficEvent.hasExtParam() && trafficEvent.getExtParam().hasObjSize()) {
            vo.setSpoilingLength(trafficEvent.getExtParam().getObjSize().getObjLength());
            vo.setSpoilingWidth(trafficEvent.getExtParam().getObjSize().getObjWidth());
            vo.setSpoilingHeight(trafficEvent.getExtParam().getObjSize().getObjHeight());
        }
        if (trafficEvent.getSubNodeIDCount() > 0) {
            vo.setSubNodeIds(StringUtils.join(trafficEvent.getSubNodeIDList(), ","));
        }
        if (trafficEvent.getNodeIDCount() > 0) {
            vo.setNodeIds(StringUtils.join(trafficEvent.getNodeIDList(), ","));
        }
        if (trafficEvent.getGpsLocsCount() > 0) {
            vo.setGpsLoc(new ArrayList<GpsLocPO>() {{
                trafficEvent.getGpsLocsList().forEach(gpsLoc -> {
                    GpsLocPO gpsLoc1 = new GpsLocPO();
                    gpsLoc1.setEle(null);
                    gpsLoc1.setLat(gpsLoc.getLat());
                    gpsLoc1.setLng(gpsLoc.getLng());
                    add(gpsLoc1);
                });
            }});
        }

        if (trafficEvent.getStLaneCount() > 0) {
            List<LaneInfoPO> laneInfoVOList = new ArrayList<>();
            trafficEvent.getStLaneList().forEach(laneInfo -> {
                LaneInfoPO laneInfoVO = new LaneInfoPO();
                laneInfoVO.setLaneType(laneInfo.getLaneType().getNumber());
                laneInfoVO.setLaneId(laneInfo.getLaneID());
                laneInfoVOList.add(laneInfoVO);
            });
            vo.setLaneInfo(laneInfoVOList);
        }
        if (trafficEvent.hasEventRadius()) {
            vo.setEventRadius(trafficEvent.getEventRadius());
        }
        if (trafficEvent.hasExtParam() && trafficEvent.getExtParam().hasAvgV()) {
            vo.setVelocity((int) (trafficEvent.getExtParam().getAvgV() * 0.02));
        }
        if (trafficEvent.getPileLocsCount() > 0) {
            vo.setPileloc(new ArrayList<PileLocPO>() {{
                trafficEvent.getPileLocsList().forEach(pileLoc -> {
                    PileLocPO pileLocVO = new PileLocPO();
                    pileLocVO.setNumber(pileLoc.getNumber());
                    pileLocVO.setDistance(pileLoc.getDistance());
                    add(pileLocVO);
                });
            }});
        }
        if (trafficEvent.hasRoadName()) {
            vo.setRoadName(trafficEvent.getRoadName());
        }
        if (trafficEvent.hasLevel()) {
            vo.setJamLevel(trafficEvent.getLevel());
        }
        if (trafficEvent.hasEventConfidence()) {
            vo.setEventConfidence(trafficEvent.getEventConfidence());
        }
        if (trafficEvent.hasReportTime()) {
            vo.setDrcEventReportTime(new Date(trafficEvent.getReportTime()));
        }
        if (trafficEvent.hasExtParam() && trafficEvent.getExtParam().getVehicleObjsCount() > 0) {
            vo.setObjects(new ArrayList<TrafficEventObjectPO>() {{
                trafficEvent.getExtParam().getVehicleObjsList().forEach(trafficObj -> {
                    TrafficEventObjectPO vo = new TrafficEventObjectPO();
                    if (trafficObj.hasObjID())
                        vo.setObjectId(trafficObj.getObjID());
                    if (trafficObj.hasObjType())
                        vo.setObjectType(trafficObj.getObjType().getNumber());
                    if (trafficObj.hasObjSpeed())
                        // 车辆速度单位是0.02m/s，需要转换成m/s
                        vo.setObjectSpeed(trafficObj.getObjSpeed() * 0.02f);
                    if (trafficObj.hasVehicleColor())
                        vo.setVehicleColor(trafficObj.getVehicleColor().replace("\u0000", "").replace("\\u0000", "").trim());
                    if (trafficObj.hasPlateType())
                        vo.setPlateType(trafficObj.getPlateType().replace("\u0000", "").replace("\\u0000", "").trim());
                    if (trafficObj.hasPlateColor())
                        vo.setPlateColor(trafficObj.getPlateColor().replace("\u0000", "").replace("\\u0000", "").trim());
                    if (trafficObj.hasPlateNum())
                        vo.setPlateNum(trafficObj.getPlateNum().replace("\u0000", "").replace("\\u0000", "").trim());
                    if (trafficObj.hasUlTrackId())
                        vo.setTrackId(trafficObj.getUlTrackId());
                    if (trafficObj.getAulTrackIdCount() > 0)
                        vo.setTrackIdListStr(StringUtils.join(trafficObj.getAulTrackIdList(), ","));
                    if (trafficObj.hasEntryTime())
                        vo.setEntryTime(trafficObj.getEntryTime());
                    if (trafficObj.hasUtmLocs()) {
                        IfDrcCrmR4.UtmLoc utmLoc = trafficObj.getUtmLocs();
//                        UtmLocPO utmLoc1 = new UtmLocPO();
//                        utmLoc1.setUtmX(utmLoc.getUtmX());
//                        utmLoc1.setUtmY(utmLoc.getUtmY());
//                        utmLoc1.setUtmZ(utmLoc.getUtmZ());
//                        vo.setUtmLoc(utmLoc1);

                        // TODO
                        vo.setUtmX(utmLoc.getUtmX());
                        vo.setUtmY(utmLoc.getUtmY());
                        vo.setUtmZ(utmLoc.getUtmZ());
                    }
                    if (trafficObj.hasTargetCameraIP())
                        vo.setTargetCameraIp(trafficObj.getTargetCameraIP().trim());

                    add(vo);
                });
            }});
        }
        if (trafficEvent.hasForbiddenZoneId()) {
            vo.setForbiddenZoneId(trafficEvent.getForbiddenZoneId());
        }
        if (trafficEvent.hasExtParam() && trafficEvent.getExtParam().hasUlPersonNumber()) {
            vo.setPersonNum(trafficEvent.getExtParam().getUlPersonNumber());
        }

        vo.setOperation(operation);
        return vo;
    }

    private TrafficEventPO updateRedisAndInsertTrafficEventImage(TrafficEventPO param) {
        TrafficEventPO redisObj = null;
        if (redisObj == null) {
            redisObj = trafficEventMapper.findOne(param.getTrafficEventId(), param.getDrcEventReportId(), null);
//            sortTrafficEventImageDesc(redisObj);
        }

//        redisObj.setTrafficEventImageUrls(param.getTrafficEventImageUrls());
        if(param.getTrafficEventImageUrls()!=null && param.getTrafficEventImageUrls().size()>0){
            //最新的图片放最前面
            if(redisObj.getTrafficEventImageUrls()!=null){
//                redisObj.getTrafficEventImageUrls().addAll(param.getTrafficEventImageUrls());
                for(TrafficEventImagePO imagePO:param.getTrafficEventImageUrls()){
                    redisObj.getTrafficEventImageUrls().add(0,imagePO);
                }
            }else {
                List<TrafficEventImagePO> imageList = new ArrayList<>(param.getTrafficEventImageUrls());
                Collections.reverse(imageList);
                redisObj.setTrafficEventImageUrls(imageList);
            }
        }

        // 存储更新图片
        trafficEventImageMapper.insertList(param.getTrafficEventImageUrls(), param.getTrafficEventId());
        log.info("insert image drcEventReportId:{}", param.getDrcEventReportId());

        return redisObj;
    }

    /**
     * 事件关联信息存储（事件历史，事件的障碍物，事件的坐标，事件的里程桩信息等）
     *
     * @param param 事件信息
     */
    private void insertTrafficEventRelevance(TrafficEventPO param) {
        List<PileLocPO> pileloc = param.getPileloc();
        if (TrafficEnum.RelievedEnum.YES.getCode().equals(param.getIsRelieved())) {
            // 解除事件的里程桩保存未解除前的里程桩数据
            TrafficEventPO one = trafficEventMapper.findOne(param.getTrafficEventId(), param.getDrcEventReportId(), null);
            if (one != null) {
                pileloc = one.getPileloc();
            }
        }

        TrafficEventHistoryPO historyPO = new TrafficEventHistoryPO();
        BeanUtils.copyProperties(param, historyPO);
        trafficEventHistoryMapper.insertOne(historyPO);

        // 事件关联信息备份(障碍物是整批替换)
        if (!CollectionUtils.isEmpty(param.getObjects())) {
            trafficEventObjectMapper.insertList(param.getObjects(), historyPO.getId());
        }

        if (!CollectionUtils.isEmpty(pileloc)) {
            trafficEventPilelocMapper.insertList(pileloc, historyPO.getId());
        }

        if (!CollectionUtils.isEmpty(param.getGpsLoc())) {
            trafficEventGpsLocMapper.insertList(param.getGpsLoc(), historyPO.getId());
        }

        if (!CollectionUtils.isEmpty(param.getUtmLoc())) {
            trafficEventUtmLocMapper.insertList(param.getUtmLoc(), historyPO.getId());
        }

        if (!CollectionUtils.isEmpty(param.getJudgeTimes())) {
            trafficEventJudgeTimeMapper.insertList(param.getJudgeTimes(), historyPO.getId());
        }

        if (!CollectionUtils.isEmpty(param.getTrafficEventImageUrls())) {
            trafficEventImageMapper.insertList(param.getTrafficEventImageUrls(), param.getTrafficEventId());
        }

        if (!CollectionUtils.isEmpty(param.getLaneInfo())) {
            trafficEventLaneMapper.insertList(param.getLaneInfo(), historyPO.getId());
        }
    }

    /**
     * 根据高精车道查询路段ID
     *
     * @param param 参数
     * @return sectionId
     */
    private String findSectionIdByHdLaneIds(TrafficEventPO param) {
        return null;
//        List<Integer> hdLandIds = MergeUtil.divisionStrToIntegerList(param.getHdLaneIds());
//        Set<Integer> sectionId = new HashSet<>();
//        for (Integer hdLandId : hdLandIds) {
//            try {
//                MapLaneDTO mapLaneDTO = mapFeignClient.mapLaneDTO(mapId, hdLandId, roadId);
//                if (mapLaneDTO != null && mapLaneDTO.getRoadSegment() != null) {
//                    sectionId.add(mapLaneDTO.getRoadSegment());
//                }
//            } catch (Exception e) {
//                log.error("find sectionIdByHdLandId error, drcEventReportId:{}", param.getDrcEventReportId());
//            }
//        }
//
//        if (CollectionUtils.isEmpty(sectionId)) {
//            try {
//                MapForbiddenZoneDTO mapForbiddenZoneDTO = mapFeignClient.mapForbiddenZoneDTO(mapId, roadId, param.getForbiddenZoneId());
//                if (mapForbiddenZoneDTO != null) {
//                    sectionId.add(mapForbiddenZoneDTO.getRoadSegment());
//                }
//            } catch (Exception e) {
//                log.error("find sectionIdByForbiddenZoneId error, drcEventReportId:{}", param.getDrcEventReportId());
//            }
//        }
//        return MergeUtil.mergeIntegerListToString(sectionId);
    }

    /**
     * 确认occurTime
     *
     * @param judgeTimes 事件判定时间
     * @return occurTime
     */
    private Date findOccurTime(List<JudgeTimePO> judgeTimes) {
        if (CollectionUtils.isEmpty(judgeTimes)) {
            return null;
        }

        return judgeTimes.stream().map(JudgeTimePO::getJudgeTime).max(Comparator.comparing(Date::getTime)).orElse(new Date());
    }

}

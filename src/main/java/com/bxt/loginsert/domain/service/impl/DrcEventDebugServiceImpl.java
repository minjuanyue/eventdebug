package com.bxt.loginsert.domain.service.impl;

import com.bxt.loginsert.domain.po.debug.*;
import com.bxt.loginsert.domain.repository.*;
import com.bxt.loginsert.domain.service.DrcEventDebugService;
import com.google.protobuf.InvalidProtocolBufferException;
import event_debug.DrcEventDebug;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class DrcEventDebugServiceImpl implements DrcEventDebugService {

    @Resource
    private DrcEventDebugAbnormalParkingMapper abnormalParkingMapper;
    @Resource
    private DrcEventDebugTrafficAccidentMapper trafficAccidentMapper;
    @Resource
    private DrcEventDebugTrafficAccidentObsMapper trafficAccidentObsMapper;
    @Resource
    private DrcEventDebugUltraLowSpeedMapper ultraLowSpeedMapper;
    @Resource
    private DrcEventDebugLowSpeedMapper lowSpeedMapper;
//    @Resource
//    private DrcEventDebugDetectorInfoMapper detectorInfoMapper;
//    @Resource
//    private DrcEventDebugDetectorInfoDetailMapper detectorInfoDetailMapper;
    @Resource
    private DrcEventDebugTrafficJamMapper trafficJamMapper;
    @Resource
    private DrcEventDebugTrafficJamEventInfoMapper trafficJamEventInfoMapper;
    @Resource
    private DrcEventDebugTrafficJamDetectorInfoMapper trafficJamDetectorInfoMapper;
//    @Resource
//    private DrcEventDebugRoadCloseMapper roadCloseMapper;
//    @Resource
//    private DrcEventDebugRoadCloseDetectorInfoMapper roadCloseDetectorInfoMapper;
//    @Resource
//    private DrcEventDebugRoadCloseEventInfoMapper roadCloseEventInfoMapper;
//    @Resource
//    private DrcEventDebugRoadCloseAdjDetectorInfoMapper roadCloseAdjDetectorInfoMapper;
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void saveAbnormalParking(byte[] msg) throws InvalidProtocolBufferException {

        DrcEventDebugAbnormalParkingPO source = getDrcEventDebugAbnormalParkingPO(msg);
        abnormalParkingMapper.insertOne(source);
    }

    @Override
    public void saveConverseRunning(byte[] msg) throws InvalidProtocolBufferException {

    }

    @Override
    public void saveExceedSpeed(byte[] msg) throws InvalidProtocolBufferException {

    }

    @Override
    public void saveJam(byte[] msg) throws InvalidProtocolBufferException {

        DrcEventDebugTrafficJamPO source = getDrcEventDebugTrafficJamPO(msg);
        trafficJamMapper.insertOne(source);

        if (!CollectionUtils.isEmpty(source.getStEventInfo())) {
            trafficJamEventInfoMapper.insertList(source.getStEventInfo(), source.getId());
        }

        if (!CollectionUtils.isEmpty(source.getStDetectorInfo())) {
            trafficJamDetectorInfoMapper.insertList(source.getStDetectorInfo(), source.getId());
        }
    }

    @Override
    public void saveLowSpeed(byte[] msg) throws InvalidProtocolBufferException {

        DrcEventDebugLowSpeedPO source = getDrcEventDebugLowSpeedPO(msg);
        lowSpeedMapper.insertSelective(source);
    }

    @Override
    public void saveRoadClose(byte[] msg) throws InvalidProtocolBufferException {

        DrcEventDebugRoadClosePO source = getDrcEventDebugRoadClosePO(msg);

//        roadCloseMapper.insertOne(source);
//
//        if (!CollectionUtils.isEmpty(source.getStEventInfo())) {
//            roadCloseEventInfoMapper.insertList(source.getStEventInfo(), source.getId());
//        }
//
//        if (!CollectionUtils.isEmpty(source.getStDetectorInfo())) {
//            for (RoadCloseDetectorInfoPO roadCloseDetectorInfoPO : source.getStDetectorInfo()) {
//                roadCloseDetectorInfoPO.setDrcEventDebugRoadCloseId(source.getId());
//                roadCloseDetectorInfoMapper.insertOne(roadCloseDetectorInfoPO);
//
//                if (!CollectionUtils.isEmpty(roadCloseDetectorInfoPO.getStAdjInfos())) {
//                    roadCloseAdjDetectorInfoMapper.insertList(roadCloseDetectorInfoPO.getStAdjInfos(), roadCloseDetectorInfoPO.getId());
//                }
//            }
//        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        Long sourceId = null;

        try {

            DrcEventDebugRoadCloseMapper mapper1 = sqlSession.getMapper(DrcEventDebugRoadCloseMapper.class);

            mapper1.insertOne(source);
            // BATCH模式下,必须commit才会返回自增id,但会造成后续有错误无法回滚
            sqlSession.commit();
            sourceId = source.getId();

            if (!CollectionUtils.isEmpty(source.getStEventInfo())) {
                DrcEventDebugRoadCloseEventInfoMapper mapper2 = sqlSession.getMapper(DrcEventDebugRoadCloseEventInfoMapper.class);
                mapper2.insertList(source.getStEventInfo(), source.getId());
                sqlSession.commit();
            }

            if (!CollectionUtils.isEmpty(source.getStDetectorInfo())) {
                DrcEventDebugRoadCloseDetectorInfoMapper mapper3 = sqlSession.getMapper(DrcEventDebugRoadCloseDetectorInfoMapper.class);
                source.getStDetectorInfo().forEach(stDetectorInfo -> {
                    stDetectorInfo.setDrcEventDebugRoadCloseId(source.getId());
                        mapper3.insertOne(stDetectorInfo);
                });
                sqlSession.commit();

                DrcEventDebugRoadCloseAdjDetectorInfoMapper mapper4 = sqlSession.getMapper(DrcEventDebugRoadCloseAdjDetectorInfoMapper.class);
                source.getStDetectorInfo().forEach(stDetectorInfo -> {
                    if (!CollectionUtils.isEmpty(stDetectorInfo.getStAdjInfos())) {
                        stDetectorInfo.getStAdjInfos().forEach(adjDetectorInfoPO -> {
                                adjDetectorInfoPO.setDrcEventDebugRoadCloseDetectorInfoId(stDetectorInfo.getId());
                                mapper4.insertOne(adjDetectorInfoPO);
                         });
                    }
                });

                sqlSession.commit();
            }
        }
        catch (Exception e) {
            sqlSession.rollback();
            log.error("saveRoadClose error id=" + sourceId);
            throw e;
        }finally {
            sqlSession.close();
        }
    }

    @Override
    public void saveTrafficAccident(byte[] msg) throws InvalidProtocolBufferException {

        DrcEventDebugTrafficAccidentPO source = getDrcEventDebugTrafficAccidentPO(msg);
        trafficAccidentMapper.insertOne(source);
        if(!CollectionUtils.isEmpty(source.getStVehicleInfos())){
            trafficAccidentObsMapper.insertList(source.getStVehicleInfos(), source.getId());
        }
    }

    @Override
    public void saveUltraLowSpeed(byte[] msg) throws InvalidProtocolBufferException {

        DrcEventDebugUltraLowSpeedPO source = getDrcEventDebugUltraLowSpeedPO(msg);
        ultraLowSpeedMapper.insertSelective(source);
    }

    @Override
    public void saveViolationPerson(byte[] msg) throws InvalidProtocolBufferException {

    }


    @Override
    public void saveDetectorInfo(byte[] msg) throws InvalidProtocolBufferException{

        DrcEventDebugDetectorInfoPO source  = getDrcEventDebugDetectorInfoPO(msg);

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);

        Long sourceId = null;

        try {

            DrcEventDebugDetectorInfoMapper mapper1 = sqlSession.getMapper(DrcEventDebugDetectorInfoMapper.class);

            if (source != null) {

                mapper1.insertSelective(source);
                sqlSession.commit();

                sourceId = source.getId();

                List<DrcEventDebugDetectorInfoDetailPO> detectorInfos = source.getDrcEventDebugDetectorInfoDetailPOS();
                if (!CollectionUtils.isEmpty(detectorInfos)) {
                    DrcEventDebugDetectorInfoDetailMapper mapper2 = sqlSession.getMapper(DrcEventDebugDetectorInfoDetailMapper.class);
                    for (DrcEventDebugDetectorInfoDetailPO po : detectorInfos) {
                        po.setDetectorInfoId(source.getId());
                        mapper2.insertSelective(po);
                    }
                    sqlSession.commit();
                }
            }
        } catch (Exception e) {
            sqlSession.rollback();
            log.error("saveDetectorInfo error id=" + sourceId);
            throw e;
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 异常停车
     */
    private DrcEventDebugAbnormalParkingPO getDrcEventDebugAbnormalParkingPO(byte[] content) throws InvalidProtocolBufferException{

        DrcEventDebug.DRC_EVENT_DEBUG_ABNORMAL_PARKING info = DrcEventDebug.DRC_EVENT_DEBUG_ABNORMAL_PARKING.parseFrom(content);
        log.debug("recv DRC_EVENT_DEBUG_ABNORMAL_PARKING:\n{}", info);

        DrcEventDebugAbnormalParkingPO.DrcEventDebugAbnormalParkingPOBuilder builder = DrcEventDebugAbnormalParkingPO.builder();

        if (info.hasFrame())
            builder.frame((long) info.getFrame());
        if (info.hasSubframe())
            builder.subFrame((long) info.getSubframe());
        if (info.hasRctpId())
            builder.rctpId((long) info.getRctpId());
        if (info.hasEventId())
            builder.eventId(info.getEventId());
        if (info.hasEventype())
            builder.eventType(info.getEventype().getNumber());
        if (info.hasState())
            builder.state(info.getState());
        if (info.hasCstate())
            builder.cState(info.getCstate());
        if (info.hasObsState())
            builder.obsState(info.getObsState());
        if (info.hasDiffDistance())
            builder.diffDistance(info.getDiffDistance());
        if (info.hasUlMaxWaitTime())
            builder.ulMaxWaitTime(((long) info.getUlMaxWaitTime()));
        if (info.hasCenterPostionX())
            builder.centerPostionX(info.getCenterPostionX());
        if (info.hasCenterPostionY())
            builder.centerPostionY(info.getCenterPostionY());
        if (info.hasDirecRad())
            builder.direcRad(info.getDirecRad());
        if (info.hasLargeMajorAxisRadius())
            builder.largeMajorAxisRadius(((long) info.getLargeMajorAxisRadius()));
        if (info.hasLargeMinorAxisRadius())
            builder.largeMinorAxisRadius(((long) info.getLargeMinorAxisRadius()));
        if (info.hasSmallMajorAxisRadius())
            builder.smallMajorAxisRadius(((long) info.getSmallMajorAxisRadius()));
        if (info.hasSmallMinorAxisRadius())
            builder.smallMinorAxisRadius(((long) info.getSmallMinorAxisRadius()));
        if (info.hasNewOBSUUID())
            builder.newObsUuid(info.getNewOBSUUID());
        if (info.hasNewOBSframe())
            builder.newObsFrame(((long) info.getNewOBSframe()));
        if (info.hasNewOBSsubframe())
            builder.newObsSubFrame(((long) info.getNewOBSsubframe()));
        if (info.hasNewOBSx())
            builder.newObsX(info.getNewOBSx());
        if (info.hasNewOBSy())
            builder.newObsY(info.getNewOBSy());
        if (info.hasNewOBSdrsu())
            builder.newObsDrsu(((long) info.getNewOBSdrsu()));
        if (info.hasNewOBSlane())
            builder.newObsLane(((long) info.getNewOBSlane()));
        if (info.hasNewOBSHDlane())
            builder.newObsHdLane(((long) info.getNewOBSHDlane()));
        if (info.hasNewOBSHDconnection())
            builder.newObsHdConnection(((long) info.getNewOBSHDconnection()));
        if (info.hasFirstOBSUUID())
            builder.firstObsUuid(info.getFirstOBSUUID());
        if (info.hasFirstOBSframe())
            builder.firstObsFrame((long) info.getFirstOBSframe());
        if (info.hasFirstOBSsubframe())
            builder.firstObsSubFrame((long) info.getFirstOBSsubframe());
        if (info.hasFirstOBSx())
            builder.firstObsX(info.getFirstOBSx());
        if (info.hasFirstOBSy())
            builder.firstObsY(info.getFirstOBSy());
        if (info.hasFirstOBSdrsu())
            builder.firstObsDrsu((long) info.getFirstOBSdrsu());
        if (info.hasFirstOBSlane())
            builder.firstObsLane((long) info.getFirstOBSlane());
        if (info.hasFirstOBSHDlane())
            builder.firstObsHdLane((long) info.getFirstOBSHDlane());
        if (info.hasFirstOBSHDconnection())
            builder.firstObsHdConnection((long) info.getFirstOBSHDconnection());
        if (info.hasStrDetectorInfo())
            builder.strDetectorInfo(info.getStrDetectorInfo());
        if (info.hasStrExtParam())
            builder.strExtParam(info.getStrExtParam());
        if (info.hasUlDrcId())
            builder.ulDrcId(info.getUlDrcId());
        if (info.hasVehicleType()) {
            builder.vehicleType(info.getVehicleType());
        }
        if (info.hasStrPlateNo()) {
            builder.strPlateNo(info.getStrPlateNo());
        }

        return builder.build();
    }

    /**
     * 堵塞
     */
    private DrcEventDebugTrafficJamPO getDrcEventDebugTrafficJamPO(byte[] content) throws InvalidProtocolBufferException {

        DrcEventDebug.DRC_EVENT_DEBUG_TRAFFIC_JAM info = DrcEventDebug.DRC_EVENT_DEBUG_TRAFFIC_JAM.parseFrom(content);
        log.debug("recv DRC_EVENT_DEBUG_TRAFFIC_JAM:\n{}", info);

        DrcEventDebugTrafficJamPO.DrcEventDebugTrafficJamPOBuilder builder = DrcEventDebugTrafficJamPO.builder();

        if (info.hasUlFrame())
            builder.ulFrame((long) info.getUlFrame());
        if (info.hasUlSubFrame())
            builder.ulSubFrame((long) info.getUlSubFrame());
        if (info.hasUlCycle())
            builder.ulCycle((long) info.getUlCycle());
        if (info.hasUlDrcId())
            builder.ulDrcId(info.getUlDrcId());

        if (info.getStEventInfoCount() > 0) {
            List<TrafficJamEventInfoPO> eventInfoVOS = new ArrayList();
            List<DrcEventDebug.TrafficJamEventInfo> eventInfoList = info.getStEventInfoList();

            for (DrcEventDebug.TrafficJamEventInfo eventInfo : eventInfoList) {
                TrafficJamEventInfoPO.TrafficJamEventInfoPOBuilder sb = TrafficJamEventInfoPO.builder();
                if (eventInfo.hasEventId())
                    sb.eventId(eventInfo.getEventId());
                if (eventInfo.hasEventype())
                    sb.evenType(eventInfo.getEventype().getNumber());
                if (eventInfo.getMergeEventIdCount() > 0)
                    sb.mergeEventId(StringUtils.join(eventInfo.getMergeEventIdList(), ","));
                if (eventInfo.getSlDetectorIdCount() > 0)
                    sb.slDetectorId(StringUtils.join(eventInfo.getSlDetectorIdList(), ","));
                if (eventInfo.hasDbUtmx())
                    sb.dbUtmX(eventInfo.getDbUtmx());
                if (eventInfo.hasDbUtmY())
                    sb.dbUtmY(eventInfo.getDbUtmY());

                eventInfoVOS.add(sb.build());
            }
            builder.stEventInfo(eventInfoVOS);
        }

        if (info.getStDetectorInfoCount() > 0) {
            List<TrafficJamDetectorInfoPO> detectorInfoVO = new ArrayList();
            List<DrcEventDebug.TrafficJamDetectorInfo> detectorInfoList = info.getStDetectorInfoList();

            for (DrcEventDebug.TrafficJamDetectorInfo detectorInfo: detectorInfoList) {
                TrafficJamDetectorInfoPO.TrafficJamDetectorInfoPOBuilder sb = TrafficJamDetectorInfoPO.builder();

                if (detectorInfo.hasSlId())
                    sb.slId(detectorInfo.getSlId());
                if (detectorInfo.hasSlState())
                    sb.slState(detectorInfo.getSlState());
                if (detectorInfo.hasDbAvgSpeed())
                    sb.dbAvgSpeed(detectorInfo.getDbAvgSpeed());
                if (detectorInfo.hasSlVels())
                    sb.slVels(detectorInfo.getSlVels());
                if (detectorInfo.hasDbUtmx())
                    sb.dbUtmX(detectorInfo.getDbUtmx());
                if (detectorInfo.hasDbUtmY())
                    sb.dbUtmY(detectorInfo.getDbUtmY());

                detectorInfoVO.add(sb.build());

            }
            builder.stDetectorInfo(detectorInfoVO);
        }

        return builder.build();
    }

    /**
     * 低速
     */
    private DrcEventDebugLowSpeedPO getDrcEventDebugLowSpeedPO(byte[] content) throws InvalidProtocolBufferException {

        DrcEventDebug.DRC_EVENT_DEBUG_LOW_SPEED info = DrcEventDebug.DRC_EVENT_DEBUG_LOW_SPEED.parseFrom(content);
        log.debug("recv DRC_EVENT_DEBUG_LOW_SPEED:\n{}", info);
        DrcEventDebugLowSpeedPO.DrcEventDebugLowSpeedPOBuilder builder = DrcEventDebugLowSpeedPO.builder();

        if (info.hasFrame())
            builder.frame(info.getFrame());
        if (info.hasSubframe())
            builder.subframe(info.getSubframe());
        if (info.hasEventId())
            builder.eventId(info.getEventId());
        if (info.hasEventype())
            builder.evenType(info.getEventype().getNumber());
        if (info.hasUlstate())
            builder.state(info.getUlstate());
        if (info.hasUlUUID())
            builder.uuid(info.getUlUUID());
        if (info.hasDbObsUtmx())
            builder.dbObsUtmX(info.getDbObsUtmx());
        if (info.hasDbObsUtmY())
            builder.dbObsUtmY(info.getDbObsUtmY());
        if (info.hasSlId())
            builder.slid(info.getSlId());
        if (info.hasDbAvgSpeed())
            builder.dbAvgSpeed(info.getDbAvgSpeed());
        if (info.hasDbObsSpeed())
            builder.dbObsSpeed(info.getDbObsSpeed());
        if (info.hasUlDrsuId())
            builder.drsuId(info.getUlDrsuId());
        if (info.hasUlLandId())
            builder.laneId(info.getUlLandId());
        if (info.hasUlHDlaneId())
            builder.hdlaneId(info.getUlHDlaneId());
        if (info.hasUlHDConnectionId())
            builder.hdconnectionId(info.getUlHDConnectionId());
        if (info.hasStrPlateNo())
            builder.plateNo(info.getStrPlateNo().replace("\u0000", "").replace("\\u0000", ""));
        if (info.hasStrDetectorInfo())
            builder.strDetectorInfo(info.getStrDetectorInfo());
        if (info.hasUlDrcId())
            builder.ulDrcId(info.getUlDrcId());
        return builder.build();
    }

    /**
     * 道路关闭
     */
    private DrcEventDebugRoadClosePO getDrcEventDebugRoadClosePO(byte[] content) throws InvalidProtocolBufferException {

        DrcEventDebug.DRC_EVENT_DEBUG_NO_CONES info = DrcEventDebug.DRC_EVENT_DEBUG_NO_CONES.parseFrom(content);
        log.debug("recv DRC_EVENT_DEBUG_NO_CONES:\n{}", info);

        DrcEventDebugRoadClosePO.DrcEventDebugRoadClosePOBuilder builder = DrcEventDebugRoadClosePO.builder();

        if (info.hasUlFrame())
            builder.ulFrame((long) info.getUlFrame());
        if (info.hasUlSubFrame())
            builder.ulSubFrame((long) info.getUlSubFrame());
        if (info.hasUlCycle())
            builder.ulCycle((long) info.getUlCycle());
        if (info.hasUlDrcId())
            builder.ulDrcId(info.getUlDrcId());

        if (info.getStEventInfoCount() > 0) {
            List<RoadCloseEventInfoPO> eventInfoVOS = new ArrayList();
            List<DrcEventDebug.NoConesEventInfo> eventInfoList = info.getStEventInfoList();

            for (DrcEventDebug.NoConesEventInfo eventInfo: eventInfoList) {
                RoadCloseEventInfoPO.RoadCloseEventInfoPOBuilder sb = RoadCloseEventInfoPO.builder();
                if (eventInfo.hasEventId())
                    sb.eventId(eventInfo.getEventId());
                if (eventInfo.hasEventype())
                    sb.evenType(eventInfo.getEventype().getNumber());
                if (eventInfo.getMergeEventIdCount() > 0)
                    sb.mergeEventId(StringUtils.join(eventInfo.getMergeEventIdList(), ","));
                if (eventInfo.getSlDetectorIdCount() > 0)
                    sb.slDetectorId(StringUtils.join(eventInfo.getSlDetectorIdList(), ","));
                if (eventInfo.hasDbUtmx())
                    sb.dbUtmX(eventInfo.getDbUtmx());
                if (eventInfo.hasDbUtmY())
                    sb.dbUtmY(eventInfo.getDbUtmY());
                if (eventInfo.hasUlLowVehicleSize())
                    sb.ulLowVehicleSize(eventInfo.getUlLowVehicleSize());
                if (eventInfo.hasUlLowVehicleThreshold())
                    sb.ulLowVehicleThreshold(eventInfo.getUlLowVehicleThreshold());
                if (eventInfo.hasUlRelieveCount())
                    sb.ulRelieveCount(eventInfo.getUlRelieveCount());

                eventInfoVOS.add(sb.build());
            }
            builder.stEventInfo(eventInfoVOS);
        }

        if (info.getStDetectorInfoCount() > 0) {
            List<RoadCloseDetectorInfoPO> detectorInfoVO = new ArrayList();
            List<DrcEventDebug.NoConesDetectorInfo> detectorInfoList = info.getStDetectorInfoList();

            for (DrcEventDebug.NoConesDetectorInfo detectorInfo: detectorInfoList) {
                RoadCloseDetectorInfoPO.RoadCloseDetectorInfoPOBuilder sb = RoadCloseDetectorInfoPO.builder();

                if (detectorInfo.hasSlId())
                    sb.slId(detectorInfo.getSlId());
                if (detectorInfo.hasSlState())
                    sb.slState(detectorInfo.getSlState());
                if (detectorInfo.hasUlVehiclesOfReach())
                    sb.ulVehiclesOfReach((long) detectorInfo.getUlVehiclesOfReach());
                if (detectorInfo.hasUlHistoryVehicles())
                    sb.ulHistoryVehicles((long) detectorInfo.getUlHistoryVehicles());
                if (detectorInfo.hasUlCycleOfNonReach())
                    sb.ulCycleOfNonReach((long) detectorInfo.getUlCycleOfNonReach());
                if (detectorInfo.hasUlMeanDt())
                    sb.ulMeanDt((long) detectorInfo.getUlMeanDt());
                if (detectorInfo.hasUlMaxDt())
                    sb.ulMaxDt((long) detectorInfo.getUlMaxDt());
                if (detectorInfo.getStAdjInfosCount() > 0) {
                    List<DrcEventDebug.NoConesAdjDetectorInfo> stAdjInfosList = detectorInfo.getStAdjInfosList();
                    List<RoadCloseAdjDetectorInfoPO> l = new ArrayList<>();
                    for (DrcEventDebug.NoConesAdjDetectorInfo adjInfo: stAdjInfosList) {
                        l.add(RoadCloseAdjDetectorInfoPO.builder().slDetectorId(adjInfo.getSlDetectorId()).dbCoeff(adjInfo.getDbCoeff()).build());
                    }
                    sb.stAdjInfos(l);
                }
                if (detectorInfo.hasDbP())
                    sb.dbP(detectorInfo.getDbP());
                if (detectorInfo.hasDbM())
                    sb.dbM(detectorInfo.getDbM());
                if (detectorInfo.hasDbS2())
                    sb.dbS2(detectorInfo.getDbS2());
                if (detectorInfo.hasSlAdjDetectorId())
                    sb.slAdjDetectorId(detectorInfo.getSlAdjDetectorId());
                if (detectorInfo.hasDbUtmx())
                    sb.dbUtmX(detectorInfo.getDbUtmx());
                if (detectorInfo.hasDbUtmY())
                    sb.dbUtmY(detectorInfo.getDbUtmY());
                if (detectorInfo.hasUlActiveSource())
                    sb.ulActiveSource(detectorInfo.getUlActiveSource());
                if (detectorInfo.hasUlActiveFrame())
                    sb.ulActiveFrame((long) detectorInfo.getUlActiveFrame());
                if (detectorInfo.hasUlActiveSubFrame())
                    sb.ulActiveSubFrame((long) detectorInfo.getUlActiveSubFrame());
                if (detectorInfo.hasUlVels()) {
                    sb.ulVels((long) detectorInfo.getUlVels());
                }

                detectorInfoVO.add(sb.build());

            }
            builder.stDetectorInfo(detectorInfoVO);
        }

        return builder.build();
    }

    /**
     * 交通意外
     */
    private DrcEventDebugTrafficAccidentPO getDrcEventDebugTrafficAccidentPO(byte[] content) throws InvalidProtocolBufferException {

        DrcEventDebug.DRC_EVENT_DEBUG_TRAFFIC_ACCIDENT info = DrcEventDebug.DRC_EVENT_DEBUG_TRAFFIC_ACCIDENT.parseFrom(content);
        log.debug("recv DRC_EVENT_DEBUG_TRAFFIC_ACCIDENT:\n{}", info);

        DrcEventDebugTrafficAccidentPO.DrcEventDebugTrafficAccidentPOBuilder builder = DrcEventDebugTrafficAccidentPO.builder();

        if (info.hasFrame())
            builder.frame((long) info.getFrame());
        if (info.hasSubframe())
            builder.subFrame((long) info.getSubframe());
        if (info.hasEventId())
            builder.eventId(info.getEventId());
        if (info.hasEventype())
            builder.eventType(info.getEventype().getNumber());
        if (info.hasState())
            builder.state(info.getState());
        if (info.getStVehicleInfosCount() > 0) {
            List<TrafficAccidentObsPO> sbs = new ArrayList();
            List<DrcEventDebug.TRAFFIC_ACCIDENT_OBS> vehicleInfosList = info.getStVehicleInfosList();
            for (DrcEventDebug.TRAFFIC_ACCIDENT_OBS vehicleInfo: vehicleInfosList) {

                TrafficAccidentObsPO.TrafficAccidentObsPOBuilder sb = TrafficAccidentObsPO.builder();

                if (vehicleInfo.hasUlUUID())
                    sb.ulUuid(vehicleInfo.getUlUUID());
                if (vehicleInfo.hasUlFrameFirst())
                    sb.ulFrameFirst((long) vehicleInfo.getUlFrameFirst());
                if (vehicleInfo.hasUlFrameSecond())
                    sb.ulFrameSecond(((long) vehicleInfo.getUlFrameSecond()));
                if (vehicleInfo.hasDbVehiclePositionX())
                    sb.dbVehiclePositionX(vehicleInfo.getDbVehiclePositionX());
                if (vehicleInfo.hasDbVehiclePositionY())
                    sb.dbVehiclePositionY(vehicleInfo.getDbVehiclePositionY());
                if (vehicleInfo.hasUlMasterDrsuid())
                    sb.ulMasterDrsuId((long) vehicleInfo.getUlMasterDrsuid());
                if (vehicleInfo.hasUlMasterLandid())
                    sb.ulMasterLandId(((long) vehicleInfo.getUlMasterLandid()));
                if (vehicleInfo.hasUlHDLaneid())
                    sb.ulHdLaneId(((long) vehicleInfo.getUlHDLaneid()));
                if (vehicleInfo.hasUlHDConnectionId())
                    sb.ulHdConnectionId(((long) vehicleInfo.getUlHDConnectionId()));
                if (vehicleInfo.hasStrPlateNo())
                    sb.strPlateNo((vehicleInfo.getStrPlateNo().replace("\u0000", "").replace("\\u0000", "")));
                if (vehicleInfo.hasIsHighFlow())
                    sb.isHighFlow(vehicleInfo.getIsHighFlow());
                if (vehicleInfo.hasUlAbParkingEventId())
                    sb.ulAbParkingEventId(vehicleInfo.getUlAbParkingEventId());

                sbs.add(sb.build());

            }
            builder.stVehicleInfos(sbs);
        }
        if (info.hasStrDetectorInfo())
            builder.strDetectorInfo(info.getStrDetectorInfo());
        if (info.hasStrExtParam())
            builder.strExtParam(info.getStrExtParam());
        if (info.hasCenterPostionX())
            builder.centerPostionX(info.getCenterPostionX());
        if (info.hasCenterPostionY())
            builder.centerPostionY(info.getCenterPostionY());
        if (info.hasUlDrcId())
            builder.ulDrcId(info.getUlDrcId());

        return builder.build();
    }

    /**
     * 超低速
     */
    private DrcEventDebugUltraLowSpeedPO getDrcEventDebugUltraLowSpeedPO(byte[] content) throws InvalidProtocolBufferException {

        DrcEventDebug.DRC_EVENT_DEBUG_ULTRA_LOW_SPEED info = DrcEventDebug.DRC_EVENT_DEBUG_ULTRA_LOW_SPEED.parseFrom(content);
        log.debug("recv DRC_EVENT_DEBUG_ULTRA_LOW_SPEED:\n{}", info);
        DrcEventDebugUltraLowSpeedPO.DrcEventDebugUltraLowSpeedPOBuilder builder = DrcEventDebugUltraLowSpeedPO.builder();

        if (info.hasFrame())
            builder.frame(info.getFrame());
        if (info.hasSubframe())
            builder.subframe(info.getSubframe());
        if (info.hasEventId())
            builder.eventId(info.getEventId());
        if (info.hasEventype())
            builder.evenType(info.getEventype().getNumber());
        if (info.hasUlstate())
            builder.state(info.getUlstate());
        if (info.hasUlUUID())
            builder.uuid(info.getUlUUID());
        if (info.hasDbObsUtmx())
            builder.dbObsUtmX(info.getDbObsUtmx());
        if (info.hasDbObsUtmY())
            builder.dbObsUtmY(info.getDbObsUtmY());
        if (info.hasSlId())
            builder.slid(info.getSlId());
        if (info.hasDbAvgSpeed())
            builder.dbAvgSpeed(info.getDbAvgSpeed());
        if (info.hasDbObsSpeed())
            builder.dbObsSpeed(info.getDbObsSpeed());
        if (info.hasUlDrsuId())
            builder.drsuId(info.getUlDrsuId());
        if (info.hasUlLandId())
            builder.laneId(info.getUlLandId());
        if (info.hasUlHDlaneId())
            builder.hdlaneId(info.getUlHDlaneId());
        if (info.hasUlHDConnectionId())
            builder.hdconnectionId(info.getUlHDConnectionId());
        if (info.hasStrPlateNo())
            builder.plateNo(info.getStrPlateNo().replace("\u0000", "").replace("\\u0000", ""));
        if (info.hasStrDetectorInfo())
            builder.strDetectorInfo(info.getStrDetectorInfo());
        if (info.hasUlDrcId())
            builder.ulDrcId(info.getUlDrcId());
        return builder.build();
    }

    /**
     * 监测器信息
     */
    private DrcEventDebugDetectorInfoPO getDrcEventDebugDetectorInfoPO(byte[] content) throws InvalidProtocolBufferException {

        DrcEventDebug.DRC_EVENT_DEBUG_DETECTOR_INFO info = DrcEventDebug.DRC_EVENT_DEBUG_DETECTOR_INFO.parseFrom(content);
        log.debug("recv DRC_EVENT_DEBUG_DETECTOR_INFO:\n{}", info);
        DrcEventDebugDetectorInfoPO.DrcEventDebugDetectorInfoPOBuilder builder = DrcEventDebugDetectorInfoPO.builder();

        if (info.hasUlFrame())
            builder.frame(info.getUlFrame());
        if (info.hasUlSubFrame())
            builder.subFrame(info.getUlSubFrame());
        if (info.hasUlDrcId())
            builder.ulDrcId(info.getUlDrcId());
        if (info.getDetectorInfosCount() > 0) {
            List<DrcEventDebug.TrafficJamDetectorInfo> detectorInfoList = info.getDetectorInfosList();
            List<DrcEventDebugDetectorInfoDetailPO> detectorInfoVO = new ArrayList();
            for (DrcEventDebug.TrafficJamDetectorInfo detectorInfo : detectorInfoList) {
                DrcEventDebugDetectorInfoDetailPO.DrcEventDebugDetectorInfoDetailPOBuilder sb = DrcEventDebugDetectorInfoDetailPO.builder();

                if (detectorInfo.hasSlId())
                    sb.slId(detectorInfo.getSlId());
                if (detectorInfo.hasSlState())
                    sb.slState(detectorInfo.getSlState());
                if (detectorInfo.hasDbAvgSpeed())
                    sb.dbAvgSpeed(detectorInfo.getDbAvgSpeed());
                if (detectorInfo.hasSlVels())
                    sb.slVels(detectorInfo.getSlVels());
                if (detectorInfo.hasDbUtmx())
                    sb.dbUtmX(detectorInfo.getDbUtmx());
                if (detectorInfo.hasDbUtmY())
                    sb.dbUtmY(detectorInfo.getDbUtmY());

                detectorInfoVO.add(sb.build());

            }
            builder.drcEventDebugDetectorInfoDetailPOS(detectorInfoVO);
        }

        return builder.build();
    }

}

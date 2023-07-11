package com.bxt.loginsert.domain.service.impl;

import com.bxt.loginsert.domain.po.track.TrackRoadInfoToZebraPO;
import com.bxt.loginsert.domain.po.track.TrackVtPositionPO;
import com.bxt.loginsert.domain.po.track.TrackZebraObstclePO;
import com.bxt.loginsert.domain.po.track.TrackZebraTrafficLigthPO;
import com.bxt.loginsert.domain.repository.track.TrackRoadInfoToZebraMapper;
import com.bxt.loginsert.domain.repository.track.TrackVtPositionMapper;
import com.bxt.loginsert.domain.repository.track.TrackZebraObstcleMapper;
import com.bxt.loginsert.domain.repository.track.TrackZebraTrafficLigthMapper;
import com.bxt.loginsert.domain.service.DrcVehiclePositionService;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import if_broadxt_zebra.IfBroadxtZebra;
import if_drc_crm.IfDrcCrmR4;
import if_proto_common.BxtCommonMathVector3D;
import if_proto_common.IfProtoCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class DrcVehiclePositionServiceImpl implements DrcVehiclePositionService {

    @Resource
    private TrackVtPositionMapper vtPositionMapper;
    @Resource
    private TrackRoadInfoToZebraMapper roadInfoToZebraMapper;
    @Resource
    private TrackZebraObstcleMapper zebraObstcleMapper;
    @Resource
    private TrackZebraTrafficLigthMapper zebraTrafficLigthMapper;


    @Override
    public void saveOrUpdateZebraRoadInfo(byte[] msg) throws InvalidProtocolBufferException {

        IfDrcCrmR4.DRC_CRM_KEY_VEHICLE_MAP_IND vehicleMap = IfDrcCrmR4.DRC_CRM_KEY_VEHICLE_MAP_IND.parseFrom(msg);

        IfBroadxtZebra.RoadInfoToZebra zebra = vehicleMap.getStrPushinfoToApp();
        TrackRoadInfoToZebraPO zebraPO = new TrackRoadInfoToZebraPO();

        if (vehicleMap.hasUlTransId()) {
            zebraPO.setUlTransId(vehicleMap.getUlTransId());
        }
        if (vehicleMap.hasUlDrcId()) {
            zebraPO.setUlDrcId(vehicleMap.getUlDrcId());
        }
        if (vehicleMap.hasUlDevType()) {
            zebraPO.setUlDevType(vehicleMap.getUlDevType());
        }
        if (vehicleMap.hasStrVtId()) {
            zebraPO.setStrVtId(vehicleMap.getStrVtId());
        }

        if (zebra.hasUlRecommendedSpeed()) {
            IfBroadxtZebra.Vector3d ulRecommendedSpeed = zebra.getUlRecommendedSpeed();
            zebraPO.setUlRecommendedSpeedX(ulRecommendedSpeed.getX());
            zebraPO.setUlRecommendedSpeedY(ulRecommendedSpeed.getY());
            zebraPO.setUlRecommendedSpeedZ(ulRecommendedSpeed.getZ());
        }
        if (zebra.hasEQuitEmgCause()) {
            zebraPO.setEQuitEmgCause(zebra.getEQuitEmgCause().getNumber());
        }
        if (zebra.hasStPosition()) {
            IfBroadxtZebra.PointUTM stPosition = zebra.getStPosition();
            zebraPO.setStPositionX(stPosition.getDbx());
            zebraPO.setStPositionY(stPosition.getDby());
            zebraPO.setStPositionZ(stPosition.getDbz());
        }
        if (zebra.hasStTheta()) {
            zebraPO.setStTheta(zebra.getStTheta());
        }
        if (zebra.hasId()) {
            zebraPO.setRoadInfoToZebraId(zebra.getId());
        }
        if (zebra.hasDrcTimestamp()) {
            zebraPO.setDrcTimestamp(zebra.getDrcTimestamp());
        }
        if (zebra.hasUlMatchFlag()) {
            zebraPO.setUlMatchFlag(zebra.getUlMatchFlag());
        }

        roadInfoToZebraMapper.insertOne(zebraPO);

        if (zebra.getAstObstcleListCount() > 0) {
            saveTrackZebraObstclePOS(zebra, zebraPO.getId());
        }
        if (zebra.getAstTrafficLigthCount() > 0) {
            saveTrackZebraTrafficLigthPOS(zebra, zebraPO.getId());
        }

    }

    private void saveTrackZebraObstclePOS(IfBroadxtZebra.RoadInfoToZebra zebra, Long zebraId) {
        List<TrackZebraObstclePO> obstclePOS = new ArrayList<>(zebra.getAstObstcleListCount());
        for (IfBroadxtZebra.ZebraObstcle obstcle : zebra.getAstObstcleListList()) {
            TrackZebraObstclePO po = new TrackZebraObstclePO();
            if (obstcle.hasId()) {
                po.setZebraObstcleId(obstcle.getId());
            }
            if (obstcle.hasEObjectType()) {
                po.setEObjectType(obstcle.getEObjectType().getNumber());
            }
            if (obstcle.hasCenter()) {
                po.setCenterX(obstcle.getCenter().getDbx());
                po.setCenterY(obstcle.getCenter().getDby());
                po.setCenterZ(obstcle.getCenter().getDbz());
            }
            if (obstcle.hasTheta()) {
                po.setTheta(obstcle.getTheta());
            }
            if (obstcle.hasLength()) {
                po.setLength(obstcle.getLength());
            }
            if (obstcle.hasWidth()) {
                po.setHeight(obstcle.getHeight());
            }
            if (obstcle.hasSpeed()) {
                po.setSpeedX(obstcle.getSpeed().getX());
                po.setSpeedY(obstcle.getSpeed().getY());
                po.setSpeedZ(obstcle.getSpeed().getZ());
            }
            if (obstcle.hasVehicleClass()) {
                po.setVehicleClass(obstcle.getVehicleClass());
            }
            if (obstcle.hasVehicleColor()) {
                po.setVehicleColor(obstcle.getVehicleColor());
            }
            if (obstcle.hasVehicleBrand()) {
                po.setVehicleBrand(obstcle.getVehicleBrand());
            }
            if (obstcle.hasVehicleModel()) {
                po.setVehicleModel(obstcle.getVehicleModel());
            }
            if (obstcle.hasVehicleStyles()) {
                po.setVehicleStyles(obstcle.getVehicleStyles());
            }
            if (obstcle.hasPlateClass()) {
                po.setPlateClass(obstcle.getPlateClass());
            }
            if (obstcle.hasPlateColor()) {
                po.setPlateColor(obstcle.getPlateColor());
            }
            if (obstcle.hasPlateNo()) {
                po.setPlateNo(obstcle.getPlateNo());
            }
            if (obstcle.hasDraTimestamp()) {
                po.setDraTimestamp(obstcle.getDraTimestamp());
            }

            po.setZebraId(zebraId);
            obstclePOS.add(po);
        }

        zebraObstcleMapper.insertList(obstclePOS);
    }

    private void saveTrackZebraTrafficLigthPOS(IfBroadxtZebra.RoadInfoToZebra zebra, Long zebraId) {

        List<TrackZebraTrafficLigthPO> zebraTrafficLigthPOS = new ArrayList<>(zebra.getAstTrafficLigthCount());
        for (IfBroadxtZebra.ZebraTrafficLigth trafficLigth : zebra.getAstTrafficLigthList()) {
            TrackZebraTrafficLigthPO po = new TrackZebraTrafficLigthPO();
            if (trafficLigth.hasStPosition()) {
                po.setStPositionX(trafficLigth.getStPosition().getDbx());
                po.setStPositionY(trafficLigth.getStPosition().getDby());
                po.setStPositionZ(trafficLigth.getStPosition().getDbz());
            }
            if (trafficLigth.hasEColor()) {
                po.setEColor(trafficLigth.getEColor().getNumber());
            }
            if (trafficLigth.hasELightType()) {
                po.setELightType(trafficLigth.getELightType().getNumber());
            }
            if (trafficLigth.hasUlIsTwinkle()) {
                po.setUlIsTwinkle(trafficLigth.getUlIsTwinkle());
            }
            if (trafficLigth.hasUlId()) {
                po.setUlId(trafficLigth.getUlId());
            }
            if (trafficLigth.hasUlCountdownTime()) {
                po.setUlCountdownTime(trafficLigth.getUlCountdownTime());
            }

            po.setZebraId(zebraId);
            zebraTrafficLigthPOS.add(po);
        }

        zebraTrafficLigthMapper.insertList(zebraTrafficLigthPOS);
    }

    @Override
    public void saveOrUpdateVtPosition(byte[] msg) throws InvalidProtocolBufferException {
        IfDrcCrmR4.DRC_CRM_VT_POSITION_IND source = IfDrcCrmR4.DRC_CRM_VT_POSITION_IND.parseFrom(msg);
        TrackVtPositionPO po = new TrackVtPositionPO();

        if (source.hasUlTransId()) {
            po.setUlTransId(source.getUlTransId());
        }
        if (source.hasUlDrcId()) {
            po.setUlDrcId(source.getUlDrcId());
        }
        if (source.hasStrVtId()) {
            po.setStrVtId(source.getStrVtId());
        }
        if (source.hasStrPlateNumber()) {
            po.setStrPlateNumber(source.getStrPlateNumber());
        }
        if (source.hasStPosition()) {
            IfProtoCommon.PointUTM pointUTM = source.getStPosition();
            po.setStPositionX(pointUTM.getDbx());
            po.setStPositionY(pointUTM.getDby());
            po.setStPositionZ(pointUTM.getDbz());
        }
        if (source.hasStSpeed()) {
            BxtCommonMathVector3D.Vector3d stSpeed = source.getStSpeed();
            po.setStSpeedX(stSpeed.getX());
            po.setStSpeedY(stSpeed.getY());
            po.setStSpeedZ(stSpeed.getZ());
        }
        if (source.hasSlLaneId()) {
            po.setSlLaneId(source.getSlLaneId());
        }
        if (source.hasSlConnectionId()) {
            po.setSlConnectionId(source.getSlConnectionId());
        }
        if (source.hasSlTimestamp()) {
            po.setSlTimestamp(source.getSlTimestamp());
        }

        vtPositionMapper.insertOne(po);
    }

    public static void main(String[] args) {
    }
}

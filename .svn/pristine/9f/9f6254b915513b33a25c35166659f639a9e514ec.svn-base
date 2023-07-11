package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.debug.RoadCloseAdjDetectorInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrcEventDebugRoadCloseAdjDetectorInfoMapper {

    void insertList(@Param("list") List<RoadCloseAdjDetectorInfoPO> stAdjInfos, @Param("drcEventDebugRoadCloseDetectorInfoId") Long drcEventDebugRoadCloseDetectorInfoId);

    void insertOne(@Param("adjDetectorInfo") RoadCloseAdjDetectorInfoPO adjDetectorInfo);
}

package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.debug.RoadCloseDetectorInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrcEventDebugRoadCloseDetectorInfoMapper {

    void insertList(@Param("list") List<RoadCloseDetectorInfoPO> stDetectorInfo, @Param("drcEventDebugRoadCloseId") Long drcEventDebugRoadCloseId);

    void insertOne(RoadCloseDetectorInfoPO stDetectorInfo);
}

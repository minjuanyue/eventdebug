package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.debug.TrafficJamDetectorInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface DrcEventDebugTrafficJamDetectorInfoMapper {

    void insertList(@Param("list") List<TrafficJamDetectorInfoPO> stDetectorInfo, @Param("drcEventDebugTrafficJamId") Long drcEventDebugTrafficJamId);
}

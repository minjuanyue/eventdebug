package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.debug.TrafficJamEventInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrcEventDebugTrafficJamEventInfoMapper {

    void insertList(@Param("list") List<TrafficJamEventInfoPO> stEventInfo, @Param("drcEventDebugTrafficJamId") Long drcEventDebugTrafficJamId);

    void insertOne(@Param("eventInfo") TrafficJamEventInfoPO eventInfo, @Param("drcEventDebugTrafficJamId") Long drcEventDebugTrafficJamId);
}

package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.debug.TrafficAccidentObsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrcEventDebugTrafficAccidentObsMapper {

    void insertOne(@Param("vehicleInfo") TrafficAccidentObsPO stVehicleInfos, @Param("eventId") Long eventId);

    void insertList(@Param("list") List<TrafficAccidentObsPO> stVehicleInfos, @Param("drcEventDebugTrafficAccidentId") Long drcEventDebugTrafficAccidentId);

}

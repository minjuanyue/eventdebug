package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.TrafficEventPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface TrafficEventMapper {

    TrafficEventPO findSignEvent(@Param("drcEventReportId") Long drcEventReportId, @Param("isRelieved") Integer isRelieved);

    void insertOne(TrafficEventPO param);

    TrafficEventPO findOne(@Param("trafficEventId") Long trafficEventId, @Param("drcEventReportId") Long drcEventReportId, @Param("updateTime") Date updateTime);

    void update(TrafficEventPO param);
}

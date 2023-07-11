package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.GpsLocPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TrafficEventGpsLocMapper {

    void insertList(@Param("list") List<GpsLocPO> gpsLocVOS, @Param("historyPrimaryKey") Integer historyPrimaryKey);
}

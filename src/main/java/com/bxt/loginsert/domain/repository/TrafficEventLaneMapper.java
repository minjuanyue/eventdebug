package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.LaneInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrafficEventLaneMapper {

    int insertList(@Param("list") List<LaneInfoPO> list, @Param("historyPrimaryKey") Integer historyPrimaryKey);

}

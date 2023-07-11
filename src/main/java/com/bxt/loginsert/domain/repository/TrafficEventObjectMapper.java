package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.TrafficEventObjectPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrafficEventObjectMapper {

    void insertList(@Param("list") List<TrafficEventObjectPO> objects, @Param("historyPrimaryKey") Integer historyPrimaryKey);

}

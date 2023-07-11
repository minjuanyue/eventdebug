package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.UtmLocPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrafficEventUtmLocMapper {

    void insertList(@Param("list") List<UtmLocPO> utmLocVOS, @Param("historyPrimaryKey") Integer historyPrimaryKey);

}

package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.JudgeTimePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrafficEventJudgeTimeMapper {

    void insertList(@Param("list") List<JudgeTimePO> judgeTimes, @Param("historyPrimaryKey") Integer historyPrimaryKey);

}

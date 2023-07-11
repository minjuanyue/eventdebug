package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.PileLocPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrafficEventPilelocMapper {

    void insertList(@Param("list") List<PileLocPO> pileloc, @Param("historyPrimaryKey") Integer historyPrimaryKey);

}

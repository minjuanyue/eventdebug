package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.debug.DrcEventDebugRoadClosePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrcEventDebugRoadCloseMapper {

    void insertOne(DrcEventDebugRoadClosePO source);

}

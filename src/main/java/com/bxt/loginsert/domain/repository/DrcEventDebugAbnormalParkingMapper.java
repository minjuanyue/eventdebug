package com.bxt.loginsert.domain.repository;

import com.bxt.loginsert.domain.po.debug.DrcEventDebugAbnormalParkingPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrcEventDebugAbnormalParkingMapper {

    void insertOne(DrcEventDebugAbnormalParkingPO source);
}

package com.bxt.loginsert.domain.service;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 智能车机轨迹
 */
public interface DrcVehiclePositionService {

   void saveOrUpdateZebraRoadInfo(byte[] msg) throws InvalidProtocolBufferException;

   void saveOrUpdateVtPosition(byte[] msg) throws InvalidProtocolBufferException;
}

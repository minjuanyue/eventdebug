package com.bxt.loginsert.domain.service;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 事件服务层
 */
public interface DrcEventService {

   void saveOrUpdateTrafficEvent(byte[] msg) throws InvalidProtocolBufferException;
}

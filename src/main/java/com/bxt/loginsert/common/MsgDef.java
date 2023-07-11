package com.bxt.loginsert.common;

import java.util.Map;

/**
 * @Author: hy
 * @Date: 2021/12/1 10:11
 */
public class MsgDef {

    public static Map<Integer, String> msg;//k:msgId v:fieldName

    public static final int DRC_CRM_EVENT_IND = 2049;

    public static final int DRC_EVENT_DEBUG_ABNORMAL_PARKING = 2061;
    public static final int DRC_EVENT_DEBUG_TRAFFIC_ACCIDENT = 2062;
    public static final int DRC_EVENT_DEBUG_VIOLATION_PERSON = 2063;
    public static final int DRC_EVENT_DEBUG_JAM = 2064;
    public static final int DRC_EVENT_DEBUG_ROAD_MAINTENANCE = 2065;
    public static final int DRC_EVENT_DEBUG_ULTRA_LOW_SPEED = 2066;
    public static final int DRC_EVENT_DEBUG_LOW_SPEED = 2067;
    public static final int DRC_EVENT_DEBUG_EXCEED_SPEED = 2068;
    public static final int DRC_EVENT_DEBUG_CONVERSE_RUNNING = 2069;
    public static final int DRC_EVENT_DEBUG_DETECTOR_INFO = 2070;

}

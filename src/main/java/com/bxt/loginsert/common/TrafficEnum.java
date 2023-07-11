package com.bxt.loginsert.common;

public class TrafficEnum {

    /**
     * 事件解除
     */
    public enum RelievedEnum {

        NO(0, "正常"),
        YES(1, "解除");

        private final Integer code;
        private final String message;

        RelievedEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static String getMessage(Integer code) {
            RelievedEnum[] values = values();
            for (RelievedEnum relievedType : values) {
                if (relievedType.getCode().equals(code)) {
                    return relievedType.getMessage();
                }
            }
            return null;
        }
    }

    /**
     * 事件审核
     */
    public enum CheckedEnum {

        NO(0, "未审核"),
        YES(1, "审核"),
        CANCELLED(2, "作废");

        private final Integer code;
        private final String message;

        CheckedEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static String getMessage(Integer code) {
            CheckedEnum[] values = values();
            for (CheckedEnum checkedEnum : values) {
                if (checkedEnum.getCode().equals(code)) {
                    return checkedEnum.getMessage();
                }
            }
            return null;
        }
    }

    /**
     * 人工确认
     */
    public enum ManualConfirmEnum {

        NO(0, "未确认"),
        YES(1, "已确认");

        private final Integer code;
        private final String message;

        ManualConfirmEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 高德flag
     */
    public enum GaoDeStatusFlagEnum {

        NEW(0, "新增"),
        UPDATE(1, "修改"),
        CANCELLED(2, "删除");

        private final Integer code;
        private final String message;

        GaoDeStatusFlagEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 高德flag
     */
    public enum GaoDeLocTypeEnum {

        MILEAGE(1, "桩号"),
        UTM(2, "坐标"),
        TOLLGATE(4, "收费站");

        private final Integer code;
        private final String message;

        GaoDeLocTypeEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 事件类型
     */
    public enum EVENT_TYPE_E {

        SINGLE_VEHICLE_TRAFFIC_ACCIDENT_WARNING(1, "单车交通事故"),
        TWO_VEHICLES_TRAFFIC_ACCIDENT_WARNING(2, "两车交通事故"),
        MULTIPLE_VEHICLES_TRAFFIC_ACCIDENT_WARNING(3, "多车交通事故"),
        SINGLE_VEHICLE_ABNORMAL_PARKING_WARNING(4, "单车异常停车"),
        TWO_VEHICLES_ABNORMAL_PARKING_WARNING(5, "两车异常停车"),
        VEHICLE_CONVERSE_RUNNING_WARNING(6, "逆行/倒车"),
        VIOLATION_PERSON_WARNING(7, "违规行人"),
        ULTRA_LOW_SPEED_WARNING(8, "前方车辆超低速行驶"),
        LOW_SPEED_WARNING(9, "前方车辆低速行驶"),
        SPOILING_WARNING(10, "道路抛洒物"),
        TRAFFIC_JAM_WARNING(11, "拥堵预警"),
        ROAD_CLOSE_WARNING(12, "道路封闭预警"),
        CROSS_LANE_DRIVING_WARNING(13, "跨车道行驶"),
        ICING_WARNING(14, "道路结冰预警"),
        WET_SLIDING_WARNING(15, "道路湿滑预警"),
        CLUSTER_FOG_WARNING(16, "团雾预警"),
        EXCEED_SPEED_WARNING(17, "前方车辆超速行驶"),
        ROAD_MAINTENANCE_WARNING(18, "修路事件预警"),
        OCCUPY_EMERGENCY_LANE_WARNING(19, "占用应急车道"),
        TRUCK_OCCUPY_THE_FAST_LANE_WARNING(20, "慢车占用快车道"),
        //drc占用事件类型21紧急刹车，请从22继续，23,24用于应急车道柔性复用
        BLOCK_PEDESTRIANS_WARNING(22, "行人被遮挡事件（用于演示）结构体同违规行人"),
        TRAFFIC_ACCIDENT_WARNING(25, "交通事故"),
        ABNORMAL_PARKING_WARNING(26, "异常停车"),
        OCCUPY_BUS_LANE_WARNING(27, "占用公交车道"),

        // 华为事件
        UNKNOWN_TYPE(0, "华为事件未知类型"),
        TRAFFIC_ACCIDENT(100, "交通事故"),
        VIOLATE_PERSON(405, "违规行人"),
        TRAFFIC_JAM(707, "交通拥堵"),
        EXCEED_SPEED(901, "车辆超速"),
        LOW_SPEED(902, "车辆慢行"),
        VIOLATE_PARKING(903, "违停"),
        MOTOR_RETROGRADE(904, "逆行"),
        RAMP_IMPORT(1004, "匝道汇入"),
        VIOLATE_CHANGE_LANE(1026, "违规变道"),
        NONMOTOR_RETROGRADE(1029, "非机动车逆行"),
        NONMOTOR_INTRUDE_MOTOR_LANE(1030, "非机动车违规变道"),
        ROAD_MAINTENANCE(1031, "道路维护"),
        TRAFFIC_FLOW_STAT(1023, "交通流量统计");

        private final Integer code;
        private final String message;

        EVENT_TYPE_E(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static String getMessage(Integer code) {
            EVENT_TYPE_E[] eventTypes = values();
            for (EVENT_TYPE_E eventType : eventTypes) {
                if (eventType.getCode().equals(code)) {
                    return eventType.getMessage();
                }
            }
            return null;
        }

        public static String toString(Integer code) {
            EVENT_TYPE_E[] eventTypes = values();
            for (EVENT_TYPE_E eventType : eventTypes) {
                if (eventType.getCode().equals(code)) {
                    return eventType.getCode()+":"+eventType.getMessage();
                }
            }
            return null;
        }
    }

    /**
     * 高德事件类型
     */
    public enum GAODE_EVENT_TYPE_E {

        SINGLE_VEHICLE_TRAFFIC_ACCIDENT_WARNING(1, "交通事故"),
        TWO_VEHICLES_TRAFFIC_ACCIDENT_WARNING(2, "交通事故"),
        MULTIPLE_VEHICLES_TRAFFIC_ACCIDENT_WARNING(3, "交通事故"),
        ROAD_CLOSE_WARNING(12, "道路施工"),
        ROAD_MAINTENANCE_WARNING(18, "道路施工"),
        TRAFFIC_ACCIDENT_WARNING(25, "交通事故"),

        // 华为事件
        TRAFFIC_ACCIDENT(100, "交通事故"),
        ROAD_MAINTENANCE(1031, "道路维护");


        private final Integer code;
        private final String message;

        GAODE_EVENT_TYPE_E(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static String getMessage(Integer code) {
            GAODE_EVENT_TYPE_E[] eventTypes = values();
            for (GAODE_EVENT_TYPE_E eventType : eventTypes) {
                if (eventType.getCode().equals(code)) {
                    return eventType.getMessage();
                }
            }
            return null;
        }
    }

    /**
     * 事件优先级枚举
     */
    public enum EVENT_PRIORITY_E {
        PRIORITY_TRAFFIC_ACCIDENT_WARNING(1, 25),
        PRIORITY_MULTIPLE_VEHICLES_TRAFFIC_ACCIDENT_WARNING(2,3), //多车事故
        PRIORITY_TWO_VEHICLES_TRAFFIC_ACCIDENT_WARNING(3, 2), //两车事故
        PRIORITY_SINGLE_VEHICLE_TRAFFIC_ACCIDENT_WARNING(4, 1), //单车事故
        PRIORITY_ROAD_MAINTENANCE_WARNING(5, 18), //修路事件
        PRIORITY_ABNORMAL_PARKING_WARNING(6, 26),
        PRIORITY_TWO_VEHICLES_ABNORMAL_PARKING_WARNING(7, 5), //两车异常停车
        PRIORITY_SINGLE_VEHICLE_ABNORMAL_PARKING_WARNING(8, 4), //单车异常停车
        PRIORITY_VEHICLE_CONVERSE_RUNNING_WARNING(9, 6), //逆行
        PRIORITY_VIOLATION_PERSON_WARNING(10, 7), //违规行人
        PRIORITY_EXCEED_SPEED_WARNING(11, 17),//超速行驶
        PRIORITY_ULTRA_LOW_SPEED_WARNING(12, 8), //超低速行驶
        PRIORITY_LOW_SPEED_WARNING(13, 9), //低速行驶
        PRIORITY_TRUCK_OCCUPY_THE_FAST_LANE_WARNING(14, 20), //大车占用快车道
        PRIORITY_SPOILING_WARNING(15, 10), //抛洒物
        PRIORITY_TRAFFIC_JAM_WARNING(16, 11), //拥堵
        PRIORITY_OCCUPY_EMERGENCY_LANE_WARNING(17, 19), //占用应急车道
        PRIORITY_ROAD_CLOSE_WARNING(18, 12), //道路封闭
        PRIORITY_CROSS_LANE_DRIVING_WARNING(19, 13), //跨车道行驶
        PRIORITY_ICING_WARNING(20 ,14), //结冰路段
        PRIORITY_WET_SLIDING_WARNING(21, 15), //湿滑路段
        PRIORITY_CLUSTER_FOG_WARNING(22, 16), //团雾
        PRIORITY_BLOCK_PEDESTRIANS_WARNING(23, 22), //行人遮挡（用于演示）结构体同违规行人
        PRIORITY_OCCUPY_BUS_LANE_WARNING(24, 27);

        private final Integer priorityCode;
        private final Integer trafficEventType;

        EVENT_PRIORITY_E(Integer priorityCode, Integer trafficEventType) {
            this.priorityCode = priorityCode;
            this.trafficEventType = trafficEventType;
        }

        public Integer getPriorityCode() {
            return priorityCode;
        }

        public Integer getTrafficEventType() {
            return trafficEventType;
        }

        public static Integer getPriorityCodeByTrafficEventType(Integer trafficEventType) {
            EVENT_PRIORITY_E[] priorityCodes = values();
            for (EVENT_PRIORITY_E priority : priorityCodes) {
                if (priority.getTrafficEventType().equals(trafficEventType)) {
                    return priority.getPriorityCode();
                }
            }
            return null;
        }

    }


    /**
     * 统计表中拥堵统计状态
     */
    public enum JamStatusEnum {

        NO(0, "正常"),
        YES(1, "废除");

        private final Integer code;
        private final String message;

        JamStatusEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 预置点归属
     */
    public enum PresetAreaBelongEnum {

        ramp(0, "匝道"),
        section(1, "路段"),
        road(2, "道路");

        private final Integer code;
        private final String name;

        PresetAreaBelongEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return name;
        }
    }

    /**
     * 预置点归属
     */
    public enum CameraTypeEnum {

        longFocus(4, "长焦"),
        shortFocus(11, "短焦");

        private final Integer code;
        private final String name;

        CameraTypeEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return name;
        }
    }

}


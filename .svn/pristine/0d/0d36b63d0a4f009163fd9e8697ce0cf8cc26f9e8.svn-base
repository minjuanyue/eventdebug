package com.bxt.loginsert.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyang
 * 对应障碍物traffic_event_object表
 */
@Data
public class TrafficEventObjectPO implements Serializable {
    private static final long serialVersionUID = 7247714666080613259L;
    private Long id;
    private Long trafficEventHistoryId;
    private Long objectId;
    private Integer objectType;
    private String vehicleColor;
    private String plateType;
    private String plateColor;
    private String plateNum;
    private Float objectSpeed;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private long trackId;
    private String trackIdListStr;
    private Long entryTime;
    private Long utmX;
    private Long utmY;
    private Long utmZ;
    private String targetCameraIp;
}

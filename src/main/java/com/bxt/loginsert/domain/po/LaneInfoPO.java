package com.bxt.loginsert.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * @author wangyang
 * 车道信息
 */
@Data
public class LaneInfoPO {

    private Long id;
    private Long trafficEventHistoryId;
    private Integer laneId;
    private Integer laneType;
    private Date createTime;
}

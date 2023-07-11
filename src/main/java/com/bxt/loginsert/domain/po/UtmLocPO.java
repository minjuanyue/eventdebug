package com.bxt.loginsert.domain.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangyang
 * 对应事件里程桩信息traffic_event_pileloc
 */
@Data
public class UtmLocPO implements Serializable {
    private static final long serialVersionUID = 7247714666080613256L;
    private Long id;
    private Long trafficEventHistoryId;
    private Long utmX;
    private Long utmY;
    private Long utmZ;
}

package com.bxt.loginsert.domain.po;

import com.bxt.loginsert.util.MergeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author wangyang
 * 对应事件表traffic_event_history
 */
@Data
public class TrafficEventHistoryPO {
    private Integer id;
    private Long trafficEventId;
    private Long drcId;
    private Integer trafficEventType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private List<Date> judgeTimes;
    private String directionIndication;
    private Integer isRelieved;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date relieveTime;
    private Long drcEventReportId;
    private Integer isMock;
    private String hdLaneIds;
    private String hdConnectionIds;
    private List<UtmLocPO> utmLocVO;
    private Integer checkStatus;
    private Integer spoilingLength;
    private Integer spoilingWidth;
    private Integer spoilingHeight;
    private String subNodeIds;
    private String nodeIds;
    private List<GpsLocPO> gpsLocVO;
    private Integer priority;
    private String laneIds;
    private Integer eventRadius;
    private Integer velocity;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private List<PileLocPO> pileloc;
    private String roadName;
    private Integer jamLevel;
    private Integer eventConfidence;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date drcEventReportTime;
    private List<TrafficEventObjectPO> objects;
    private String trafficEventCause;
    private Integer forbiddenZoneId;
    private String sectionId;

//    public void transformData(TrafficEventPO source) {
//        this.hdLaneIds = MergeUtil.mergeLongListToString(source.getHdLaneIds());
//        this.hdConnectionIds = MergeUtil.mergeLongListToString(source.getHdConnectionIds());
//        this.subNodeIds = MergeUtil.mergeLongListToString(source.getSubNodeIds());
//        this.nodeIds = MergeUtil.mergeLongListToString(source.getNodeIds());
//    }
}


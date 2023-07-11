package com.bxt.loginsert.domain.po.debug;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoadCloseEventInfoPO {

    private Long drcEventDebugRoadCloseId;
    private Long eventId;
    private Integer evenType;
    private String mergeEventId;
    private String slDetectorId;
    private Double dbUtmX;
    private Double dbUtmY;
    private Integer ulLowVehicleSize;
    private Integer ulLowVehicleThreshold;

    private Integer ulRelieveCount;
}

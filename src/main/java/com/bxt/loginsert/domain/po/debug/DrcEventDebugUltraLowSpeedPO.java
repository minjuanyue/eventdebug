package com.bxt.loginsert.domain.po.debug;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrcEventDebugUltraLowSpeedPO {

    private Long id;

    private Integer frame;

    private Integer subframe;

    private Long eventId;

    private Integer evenType;

    private Integer state;

    private Long uuid;

    private Double dbObsUtmX;

    private Double dbObsUtmY;

    private Integer slid;

    private Double dbAvgSpeed;

    private Double dbObsSpeed;

    private Integer drsuId;

    private Integer laneId;

    private Integer hdlaneId;

    private Integer hdconnectionId;

    private String plateNo;

    private String strDetectorInfo;

    private Integer ulDrcId;
}

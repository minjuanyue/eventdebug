package com.bxt.loginsert.domain.po.debug;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DrcEventDebugTrafficAccidentPO {

    private Long id;

    private Long frame;

    private Long subFrame;

    private Long eventId;

    private Integer eventType;

    private Integer state;

    private List<TrafficAccidentObsPO> stVehicleInfos;

    private String strDetectorInfo;

    private String strExtParam;

    private Double centerPostionX;

    private Double centerPostionY;

    private Integer ulDrcId;
}

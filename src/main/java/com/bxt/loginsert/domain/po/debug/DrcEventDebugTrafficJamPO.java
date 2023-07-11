package com.bxt.loginsert.domain.po.debug;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * drc拥堵debug
 */
@Data
@Builder
public class DrcEventDebugTrafficJamPO {

    private Long id;

    private Long ulFrame;

    private Long ulSubFrame;

    private Long ulCycle;

    private List<TrafficJamEventInfoPO> stEventInfo;

    private List<TrafficJamDetectorInfoPO> stDetectorInfo;

    private Integer ulDrcId;

}

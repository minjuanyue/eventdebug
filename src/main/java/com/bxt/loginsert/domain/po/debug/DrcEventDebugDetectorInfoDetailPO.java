package com.bxt.loginsert.domain.po.debug;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrcEventDebugDetectorInfoDetailPO {
    private Long id;

    private Integer slId;

    private Integer slState;

    private Double dbAvgSpeed;

    private Integer slVels;

    private Double dbUtmX;

    private Double dbUtmY;

    private Long detectorInfoId;

}
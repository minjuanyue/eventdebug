package com.bxt.loginsert.domain.po.track;

import lombok.Data;

@Data
public class TrackZebraTrafficLigthPO {

    private Long zebraId;

    private Double stPositionX;
    private Double stPositionY;
    private Double stPositionZ;

    private Integer eColor;
    private Integer eLightType;
    private Integer ulIsTwinkle;
    private Integer ulCountdownTime;
    private Integer ulId;
}

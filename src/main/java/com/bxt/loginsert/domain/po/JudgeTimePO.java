package com.bxt.loginsert.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyang
 * 对应事件里程桩信息traffic_event_judge_time
 */
@Data
public class JudgeTimePO implements Serializable {
    private static final long serialVersionUID = 7247714666080613255L;
    private Long id;
    private Long trafficEventHistoryId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date judgeTime;

    public JudgeTimePO(){

    }

    public JudgeTimePO(Date judgeTime){
        this.judgeTime = judgeTime;
    }
}

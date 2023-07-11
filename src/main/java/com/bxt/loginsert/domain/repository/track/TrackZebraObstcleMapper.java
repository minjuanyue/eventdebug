package com.bxt.loginsert.domain.repository.track;

import com.bxt.loginsert.domain.po.TrafficEventImagePO;
import com.bxt.loginsert.domain.po.track.TrackRoadInfoToZebraPO;
import com.bxt.loginsert.domain.po.track.TrackZebraObstclePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrackZebraObstcleMapper {

    int insertList(@Param("list") List<TrackZebraObstclePO> trackZebraObstclePOS);
}

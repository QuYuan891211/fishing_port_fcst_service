package com.nmefc.observe_service.mapper;

import com.nmefc.observe_service.bean.FishingPortConfig;
import com.nmefc.observe_service.bean.FishingPortConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FishingPortConfigMapper {
    long countByExample(FishingPortConfigExample example);

    int deleteByExample(FishingPortConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FishingPortConfig record);

    int insertSelective(FishingPortConfig record);

    List<FishingPortConfig> selectByExample(FishingPortConfigExample example);

    FishingPortConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FishingPortConfig record, @Param("example") FishingPortConfigExample example);

    int updateByExample(@Param("record") FishingPortConfig record, @Param("example") FishingPortConfigExample example);

    int updateByPrimaryKeySelective(FishingPortConfig record);

    int updateByPrimaryKey(FishingPortConfig record);
}
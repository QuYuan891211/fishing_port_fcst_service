package com.nmefc.observe_service.mapper;

import com.nmefc.observe_service.bean.FcstData;
import com.nmefc.observe_service.bean.FcstDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FcstDataMapper {
    long countByExample(FcstDataExample example);

    int deleteByExample(FcstDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FcstData record);

    int insertSelective(FcstData record);

    List<FcstData> selectByExample(FcstDataExample example);

    FcstData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FcstData record, @Param("example") FcstDataExample example);

    int updateByExample(@Param("record") FcstData record, @Param("example") FcstDataExample example);

    int updateByPrimaryKeySelective(FcstData record);

    int updateByPrimaryKey(FcstData record);
}
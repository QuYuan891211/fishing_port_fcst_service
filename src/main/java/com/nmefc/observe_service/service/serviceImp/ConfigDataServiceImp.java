package com.nmefc.observe_service.service.serviceImp;

import com.nmefc.observe_service.bean.FishingPortConfig;
import com.nmefc.observe_service.bean.FishingPortConfigExample;
import com.nmefc.observe_service.mapper.FishingPortConfigMapper;
import com.nmefc.observe_service.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 获取基本信息
 * @Date 2023/1/6 14:43
 * @Author Qu Yuan
 **/
@Service("configService")
public class ConfigDataServiceImp implements ConfigService {
    @Autowired
    FishingPortConfigMapper fishingPortConfigMapper;
    @Override
    public List<FishingPortConfig> getDataByQuery(FishingPortConfigExample fishingPortConfigExample) {
        return fishingPortConfigMapper.selectByExample(fishingPortConfigExample);
    }


    @Override
    public List<FishingPortConfig> getAll() {
        FishingPortConfigExample fishingPortConfigExample = new FishingPortConfigExample();
        return getDataByQuery(fishingPortConfigExample);
    }
}

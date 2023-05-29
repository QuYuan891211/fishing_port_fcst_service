package com.nmefc.observe_service.service;

import com.nmefc.observe_service.bean.FishingPortConfig;
import com.nmefc.observe_service.bean.FishingPortConfigExample;

import java.util.List;

public interface ConfigService extends BaseService<FishingPortConfig, FishingPortConfigExample>{
//    List<> getDataByQuery(BuoyDataExample buoyDataExample);

//    List<BuoyData> loadOneBuoy(String name);

    List<FishingPortConfig> getAll();
}

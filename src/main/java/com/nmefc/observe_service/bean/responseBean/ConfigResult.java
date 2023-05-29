package com.nmefc.observe_service.bean.responseBean;

import com.nmefc.observe_service.bean.FishingPortConfig;

import java.util.List;

/**
 * @Description
 * @Date 2023/1/6 15:10
 * @Author Qu Yuan
 **/
public class ConfigResult {
    public List<FishingPortConfig> getFishingPortConfigList() {
        return fishingPortConfigList;
    }

    public void setObserveConfigList(List<FishingPortConfig> fishingPortConfigList) {
        this.fishingPortConfigList = fishingPortConfigList;
    }

    public CommonResultCode getCommonResultCode() {
        return commonResultCode;
    }

    public void setCommonResultCode(CommonResultCode commonResultCode) {
        this.commonResultCode = commonResultCode;
    }

    private List<FishingPortConfig> fishingPortConfigList;

    private CommonResultCode commonResultCode;
}

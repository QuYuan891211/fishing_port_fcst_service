package com.nmefc.observe_service.bean.responseBean;

//import com.nmefc.observice_service.bean.responseBean.middleBean.LastSingleBuoyData;

import com.nmefc.observe_service.bean.FcstData;
import com.nmefc.observe_service.bean.middleBean.HomeQueryResult;

import java.util.List;

public class LoadOneFcstResult {

    public List<HomeQueryResult> homeQueryResultList;

    public List<HomeQueryResult> getHomeQueryResultList() {
        return homeQueryResultList;
    }

    public void setHomeQueryResultList(List<HomeQueryResult> homeQueryResultList) {
        this.homeQueryResultList = homeQueryResultList;
    }

    public List<FcstData> getFcstDataList() {
        return fcstDataList;
    }

    public void setFcstDataList(List<FcstData> fcstDataList) {
        this.fcstDataList = fcstDataList;
    }

    private List<FcstData> fcstDataList;

    private CommonResultCode commonResultCode;

    public CommonResultCode getCommonResultCode() {
        return commonResultCode;
    }

    public void setCommonResultCode(CommonResultCode commonResultCode) {
        this.commonResultCode = commonResultCode;
    }


}

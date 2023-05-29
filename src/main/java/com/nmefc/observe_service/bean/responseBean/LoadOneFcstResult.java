package com.nmefc.observe_service.bean.responseBean;

//import com.nmefc.observice_service.bean.responseBean.middleBean.LastSingleBuoyData;

import com.nmefc.observe_service.bean.FcstData;

import java.util.List;

public class LoadOneFcstResult {


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

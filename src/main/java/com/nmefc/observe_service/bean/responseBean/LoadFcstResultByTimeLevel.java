package com.nmefc.observe_service.bean.responseBean;

import com.nmefc.observe_service.bean.middleBean.HomeQueryResult;

import java.util.List;

/**
 * @Description
 * @Date 2023/6/4 18:52
 * @Author Qu Yuan
 **/
public class LoadFcstResultByTimeLevel extends LoadOneFcstResult{


    public List<HomeQueryResult> getHomeQueryResultList() {
        return homeQueryResultList;
    }

    public void setHomeQueryResultList(List<HomeQueryResult> homeQueryResultList) {
        this.homeQueryResultList = homeQueryResultList;
    }
}

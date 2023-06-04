package com.nmefc.observe_service.bean.middleBean;

import java.util.List;

/**
 * @Description
 * @Date 2023/6/4 15:58
 * @Author Qu Yuan
 **/
public class HomeQueryResult {
    public String site;
    public List<TimeLevelResult> timeLevelResultList;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<TimeLevelResult> getTimeLevelResultList() {
        return timeLevelResultList;
    }

    public void setTimeLevelResultList(List<TimeLevelResult> timeLevelResultList) {
        this.timeLevelResultList = timeLevelResultList;
    }

}

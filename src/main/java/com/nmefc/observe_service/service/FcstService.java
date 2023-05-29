package com.nmefc.observe_service.service;

//import com.nmefc.observice_service.bean.responseBean.middleBean.LastSingleBuoyData;

import com.nmefc.observe_service.bean.FcstData;
import com.nmefc.observe_service.bean.FcstDataExample;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Date 2023/1/3 21:10
 * @Author Qu Yuan
 **/
public interface FcstService extends BaseService<FcstData, FcstDataExample> {
    /**
     * 获取浮标的指定最近天数据(指定或全部)
     * @param name
     * @return
     */
    List<FcstData> loadLastData(Integer days, String name) throws ParseException;


//    List<BuoyData> loadAllBuoy(Integer days);

    /**
     * 获取指定日期的单个浮标数据
     * @param start
     * @param end
     * @param name
     * @return
     */
    List<FcstData> query(Date start, Date end, String name);

    /**
     * 获取每个浮标最新的一个时次的数据
     * @param buoyDataList
     * @return
     */
    List<FcstData> filterLastOneData(List<FcstData> buoyDataList);

    /**
     * 获取各个浮标最新的且有效波高有数的
     * TODO：暂时取消有效波高的数据设定，考虑到气象使用
     * @param days
     * @param name
     * @return
     */
    List<FcstData> loadLastDataWithZBGData(Integer days,String name) throws ParseException;

    /**
     * 获取到报情况
     * @return
     */
    Long statisticsNow() throws ParseException;

    /**
     * 获取指定时间的全部浮标数据
     * @param time
     * @return
     */
    List<FcstData> queryAll(Date time);
}

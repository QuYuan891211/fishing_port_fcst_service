package com.nmefc.observe_service.controller;

import com.nmefc.observe_service.bean.FcstData;
import com.nmefc.observe_service.bean.responseBean.CommonResultCode;
import com.nmefc.observe_service.bean.responseBean.LoadOneFcstResult;
import com.nmefc.observe_service.bean.responseBean.StatisticsResult;
import com.nmefc.observe_service.service.FcstService;
import com.nmefc.observe_service.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Date 2023/1/3 21:05
 * @Author Qu Yuan
 **/
@CrossOrigin
@RestController
@RequestMapping("/fcst")
public class FcstDataController {
    @Autowired
    FcstService fcstService;

    /**
     * 查询某历史时刻所有浮标数据
     * @param time
     * @return
     */
    @GetMapping("/queryAll")
    public LoadOneFcstResult queryAll(String time){
        LoadOneFcstResult loadOneFcstResult = new LoadOneFcstResult();
        CommonResultCode commonResultCode = new CommonResultCode();
        if( null == time){
            return errorParameterMessage(loadOneFcstResult,commonResultCode);
        }
        Date datetime;
        try {
            datetime = TimeUtils.UTCToCST(time, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<FcstData> fcstDataList = null;
        fcstDataList = fcstService.queryAll(datetime);
        //数据库未查到时
        if (null == fcstDataList || fcstDataList.size() < 1){
            SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
//            String timeMessage = f.format(startTime) + "-" + f.format(endTime);
            String timeMessage = "所选时间";
            return nullDataMessage(loadOneFcstResult,commonResultCode,timeMessage);
        }
        commonResultCode.setCode("100");
        commonResultCode.setMessage("查询成功");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        loadOneFcstResult.setFcstDataList(fcstDataList);
        return loadOneFcstResult;
    }
    /**
     * 用户自定义时间查询单一浮标数据
     * @param start
     * @param end
     * @param name
     * @return
     */
    @GetMapping("/query")
    public LoadOneFcstResult query(String start, String end, String name){
        LoadOneFcstResult loadOneFcstResult = new LoadOneFcstResult();
        CommonResultCode commonResultCode = new CommonResultCode();
        if(null == start || null == end || null == name){
            return errorParameterMessage(loadOneFcstResult,commonResultCode);
        }
        Date startTime;
        Date endTime;

        //传入参数为空时，返回错误信息
        try {
            startTime = TimeUtils.UTCToCST(start, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            endTime = TimeUtils.UTCToCST(end, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Integer differ = TimeUtils.getDayDiffer(startTime,endTime);
            if(31 < differ){
                return errorDateRange(loadOneFcstResult,commonResultCode);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<FcstData> fcstDataList = null;
        fcstDataList = fcstService.query(startTime, endTime, name);
        //数据库未查到时
        if (null == fcstDataList || fcstDataList.size() < 1){
            SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
//            String timeMessage = f.format(startTime) + "-" + f.format(endTime);
            String timeMessage = "所选时间";
            return nullDataMessage(loadOneFcstResult,commonResultCode,timeMessage);
        }
        commonResultCode.setCode("100");
        commonResultCode.setMessage("查询成功");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        loadOneFcstResult.setFcstDataList(fcstDataList);
        return loadOneFcstResult;
    }
    /**
     * 获取指定浮标，最近days天（days为传入参数）的浮标数据
     * @param name
     * @param days
     * @return
     */
    @GetMapping("/getData")
    public LoadOneFcstResult getData(Integer days){
        LoadOneFcstResult loadOneFcstResult = new LoadOneFcstResult();
        CommonResultCode commonResultCode = new CommonResultCode();
        //传入参数为空时，返回错误信息
        if(null == days){
            return errorParameterMessage(loadOneFcstResult,commonResultCode);
        }
        if(31 < days){
            return errorDateRange(loadOneFcstResult,commonResultCode);
        }
        List<FcstData> fcstDataList = null;
        try {
            fcstDataList = fcstService.queryAllByTime(days);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //数据库未查到时
        if (null == fcstDataList || fcstDataList.size() < 1){
            return nullDataMessage(loadOneFcstResult,commonResultCode, "最近" + days.toString() + "天");
        }

        commonResultCode.setCode("100");
        commonResultCode.setMessage("查询成功");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        loadOneFcstResult.setFcstDataList(fcstDataList);
        return loadOneFcstResult;
    }
    /**
     * 获取指定浮标，最近days天（days为传入参数）的浮标数据
     * @param name
     * @param days
     * @return
     */
    @GetMapping("/lastSingle")
    public LoadOneFcstResult lastSingleData(Integer days, String name){
        LoadOneFcstResult loadOneFcstResult = new LoadOneFcstResult();
        CommonResultCode commonResultCode = new CommonResultCode();
        //传入参数为空时，返回错误信息
        if(null == name || null == days){
           return errorParameterMessage(loadOneFcstResult,commonResultCode);
        }
        if(31 < days){
            return errorDateRange(loadOneFcstResult,commonResultCode);
        }
        List<FcstData> fcstDataList = null;
        try {
            fcstDataList = fcstService.loadLastData(days,name);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //数据库未查到时
        if (null == fcstDataList || fcstDataList.size() < 1){
            return nullDataMessage(loadOneFcstResult,commonResultCode, "最近" + days.toString() + "天");
        }

        commonResultCode.setCode("100");
        commonResultCode.setMessage("查询成功");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        loadOneFcstResult.setFcstDataList(fcstDataList);
        return loadOneFcstResult;
    }

    /**
     * 获取全部浮标，最近days天（days为传入参数）的浮标数据
     * @param days
     * @return
     */
    @GetMapping("/lastAll")
    public LoadOneFcstResult LastAllData(Integer days){
        LoadOneFcstResult loadOneFcstResult = new LoadOneFcstResult();
        CommonResultCode commonResultCode = new CommonResultCode();
        //传入参数为空时，返回错误信息
        if(null == days){
            return errorParameterMessage(loadOneFcstResult,commonResultCode);
        }
        if(31 < days){
            return errorDateRange(loadOneFcstResult,commonResultCode);
        }
        List<FcstData> fcstDataList = null;
        try {
            fcstDataList = fcstService.loadLastData(days,null);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //数据库未查到时
        if (null == fcstDataList || fcstDataList.size() < 1){
            return nullDataMessage(loadOneFcstResult,commonResultCode, "最近" + days.toString() + "天");
        }


        commonResultCode.setCode("100");
        commonResultCode.setMessage("查询成功");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        loadOneFcstResult.setFcstDataList(fcstDataList);
        return loadOneFcstResult;
    }

    /**
     * 统计到报情况
     * @return
     * @throws ParseException
     */
    @GetMapping("/statistics")
    public StatisticsResult statistics() throws ParseException {
        StatisticsResult statisticsResult = new StatisticsResult();
        CommonResultCode commonResultCode = new CommonResultCode();

        Long num = fcstService.statisticsNow();
        //数据库未查到时
        if (null == num){
            return errorSystem(statisticsResult,commonResultCode);
        }
        commonResultCode.setCode("100");
        commonResultCode.setMessage("查询成功");
        statisticsResult.setCommonResultCode(commonResultCode);
        statisticsResult.setNum(num);
        return statisticsResult;
    }
    /**
     * 获取全部浮标，最近days天（days为传入参数）的浮标数据,并返回每个浮标最新的数据
     * @param days
     * @return
     */
    @GetMapping("/filterlastAll")
    public LoadOneFcstResult filterLastAllData(Integer days) throws ParseException {
        LoadOneFcstResult loadOneFcstResult = new LoadOneFcstResult();
        CommonResultCode commonResultCode = new CommonResultCode();
        //传入参数为空时，返回错误信息
        if(null == days){
            return errorParameterMessage(loadOneFcstResult,commonResultCode);
        }
        if(31 < days){
            return errorDateRange(loadOneFcstResult,commonResultCode);
        }
        List<FcstData> fcstDataList = null;
        fcstDataList = fcstService.loadLastDataWithZBGData(days,null);
        //数据库未查到时
        if (null == fcstDataList || fcstDataList.size() < 1){
            return nullDataMessage(loadOneFcstResult,commonResultCode, "最近" + days.toString() + "天");
        }

        List<FcstData> filterList = null;
        filterList = fcstService.filterLastOneData(fcstDataList);
        commonResultCode.setCode("100");
        commonResultCode.setMessage("查询成功");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        loadOneFcstResult.setFcstDataList(filterList);
        return loadOneFcstResult;
    }

    private LoadOneFcstResult errorParameterMessage(LoadOneFcstResult loadOneFcstResult, CommonResultCode commonResultCode) {
        commonResultCode.setCode("400");
        commonResultCode.setMessage("参数错误, 请联系系统管理员");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        return loadOneFcstResult;
    }
    private LoadOneFcstResult nullDataMessage(LoadOneFcstResult loadOneFcstResult, CommonResultCode commonResultCode, String time) {
        commonResultCode.setCode("500");
        commonResultCode.setMessage(time + "范围内，该点位观测数据未到报");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        return loadOneFcstResult;
    }

    private LoadOneFcstResult errorDateRange(LoadOneFcstResult loadOneFcstResult, CommonResultCode commonResultCode) {
        commonResultCode.setCode("600");
        commonResultCode.setMessage("暂不支持检索时间范围超过1个月，请重新选择时间");
        loadOneFcstResult.setCommonResultCode(commonResultCode);
        return loadOneFcstResult;
    }

    private StatisticsResult errorSystem(StatisticsResult statisticsResult, CommonResultCode commonResultCode) {
        commonResultCode.setCode("700");
        commonResultCode.setMessage("发生未知错误，请联系系统管理员");
        statisticsResult.setCommonResultCode(commonResultCode);
        return statisticsResult;
    }


}

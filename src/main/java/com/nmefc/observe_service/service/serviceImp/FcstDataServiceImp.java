package com.nmefc.observe_service.service.serviceImp;

//import com.nmefc.observice_service.bean.responseBean.middleBean.LastSingleFcstData;
import com.nmefc.observe_service.bean.FcstData;
import com.nmefc.observe_service.bean.FcstDataExample;
import com.nmefc.observe_service.bean.middleBean.HomeQueryResult;
import com.nmefc.observe_service.bean.middleBean.TimeLevelResult;
import com.nmefc.observe_service.mapper.FcstDataMapper;
import com.nmefc.observe_service.service.FcstService;
import com.nmefc.observe_service.service.FcstService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service("fcstDataService")
public class FcstDataServiceImp implements FcstService {
    @Autowired
    FcstDataMapper fcstDataMapper;
    String testDate1 = "2022-12-18 01:00:00";
    String testDate2 = "2023-01-01 20:00:20";
    @Override
    public List<FcstData> getDataByQuery(FcstDataExample fcstDataExample) {
        fcstDataExample.setOrderByClause("query_time ASC");
        return fcstDataMapper.selectByExample(fcstDataExample);
    }


    @Override
    public List<FcstData> loadLastData(Integer days,String name) throws ParseException {
        List<FcstData> fcstDataArrayList = new ArrayList<>();

        //TODO：生产环境中需要注释：开发环境指定当前日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = dateFormat.parse(testDate2);

        //TODO：生产环境中需要取消此注释：获取当前日期
//        Date start = new Date();

        //创建Calendar实例
        Calendar cal = Calendar.getInstance();
        //设置当前时间
        cal.setTime(start);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + days);
        //获取days天前的日期：此时start为当前系统时间，end为days天前时间
        Date end = cal.getTime();


        FcstDataExample fcstDataExample = new FcstDataExample();
        FcstDataExample.Criteria criteria =  fcstDataExample.createCriteria();
        //检索浮标名称等于传入的name，并且时间在传入时间前days天的数据
        if(null != name){
            criteria.andSiteEqualTo(name);
        }
        criteria.andQueryTimeBetween(start,end);
        try{
            fcstDataArrayList = getDataByQuery(fcstDataExample);
        }catch (Exception e){
            throw e;
        }
        return fcstDataArrayList;
    }

    /**
     * 获取各个浮标最新的且有效波高有的数据
     * @param days
     * @param name
     * @return
     */
    @Override
    public List<FcstData> loadLastDataWithZBGData(Integer days,String name) throws ParseException {
        List<FcstData> fcstDataArrayList = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //TODO：生产环境中需要注释：开发环境指定当前日期
        Date start = dateFormat.parse(testDate2);

        //TODO：生产环境中需要取消此注释：获取当前日期
//        String datestr = dateFormat.format(new Date());
//        Date start = dateFormat.parse(datestr);

        //创建Calendar实例
        Calendar cal = Calendar.getInstance();
        //设置当前时间
        cal.setTime(start);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + days);
        //获取days天前的日期：此时start为当前系统时间，end为days天前时间
        Date end = cal.getTime();


        FcstDataExample fcstDataExample = new FcstDataExample();
        FcstDataExample.Criteria criteria =  fcstDataExample.createCriteria();
        //检索浮标名称等于传入的name，并且时间在传入时间前days天的数据
        if(null != name){
            criteria.andSiteEqualTo(name);
        }
        criteria.andQueryTimeBetween(start,end);
//        criteria.andZbgIsNotNull();
        try{
            fcstDataArrayList = getDataByQuery(fcstDataExample);
        }catch (Exception e){
            throw e;
        }
        return fcstDataArrayList;
    }

    /**
     * 统计到报情况
     * @return
     * @throws ParseException
     */
    @Override
    public Long statisticsNow() throws ParseException {
        Long num;
//        Statistic statistic = new Statistic();
//        List<FcstData> fcstDataArrayList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");

        //TODO：生产环境中需要注释：开发环境指定当前日期
        Date start = dateFormat.parse(testDate2);

        //TODO：生产环境中需要取消此注释：获取当前日期
//        String datestr = dateFormat.format(new Date());
//        Date start = dateFormat.parse(datestr);

        //创建Calendar实例
        Calendar cal = Calendar.getInstance();
        //设置当前时间
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
        //获取days天前的日期：此时start为当前系统时间，end为days天前时间
        Date target = cal.getTime();
        FcstDataExample fcstDataExample = new FcstDataExample();
        FcstDataExample.Criteria criteria =  fcstDataExample.createCriteria();
        criteria.andQueryTimeEqualTo(target);
        try{
            num = fcstDataMapper.countByExample(fcstDataExample);
        }catch (Exception e){
            throw e;
        }



        return num;
    }

    /**
     * 获取指定时刻的全部浮标数据
     * @param time
     * @return
     */
    @Override
    public List<FcstData> queryAll(Date time) {
        List<FcstData> fcstDataArrayList = new ArrayList<>();
        FcstDataExample fcstDataExample = new FcstDataExample();
        FcstDataExample.Criteria criteria =  fcstDataExample.createCriteria();
        criteria.andQueryTimeEqualTo(time);
        try{
            fcstDataArrayList = getDataByQuery(fcstDataExample);
        }catch (Exception e){
            throw e;
        }
        return fcstDataArrayList;
    }

    @Override
    public List<FcstData> queryAllByTime(Integer days) throws ParseException {
        List<FcstData> fcstDataArrayList = new ArrayList<>();

        //TODO：生产环境中需要注释：开发环境指定当前日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date start = dateFormat.parse(testDate2);

        //TODO：生产环境中需要取消此注释：获取当前日期
//        Date start = new Date();

        //创建Calendar实例
        Calendar cal = Calendar.getInstance();
        //设置当前时间
        cal.setTime(start);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + days);
        //获取days天前的日期：此时start为当前系统时间，end为days天前时间
        Date end = cal.getTime();


        FcstDataExample fcstDataExample = new FcstDataExample();
        FcstDataExample.Criteria criteria =  fcstDataExample.createCriteria();
        criteria.andQueryTimeBetween(start,end);
        try{
            fcstDataArrayList = getDataByQuery(fcstDataExample);
        }catch (Exception e){
            throw e;
        }
        return fcstDataArrayList;
    }

    /**
     * 获取指定浮标的指定时间段的数据
     * @param end
     * @param start
     * @param name
     * @return
     */
    @Override
    public List<FcstData> query(Date start, Date end, String name) {
        List<FcstData> fcstDataArrayList = new ArrayList<>();
        FcstDataExample fcstDataExample = new FcstDataExample();
        FcstDataExample.Criteria criteria =  fcstDataExample.createCriteria();
        criteria.andSiteEqualTo(name);
        criteria.andQueryTimeBetween(start,end);
        try{
            fcstDataArrayList = getDataByQuery(fcstDataExample);
        }catch (Exception e){
            throw e;
        }
        return fcstDataArrayList;
    }

    @Override
    public List<FcstData> filterLastOneData(List<FcstData> fcstDataList) {
        List<FcstData> filterList = new ArrayList<FcstData>();
        Collections.reverse(fcstDataList);
        //去重
        Set<FcstData> set = new TreeSet<FcstData>(new Comparator<FcstData>(){
            @Override
            public int compare(FcstData a, FcstData b) {
                return a.getSite().compareTo(b.getSite());
            }
        });
        set.addAll(fcstDataList);
        filterList = new ArrayList<FcstData>(set);
        filterList.sort(new Comparator<FcstData>() {
            @Override
            public int compare(FcstData fcstData1, FcstData fcstData2) {
                String site1 = fcstData1.getSite();
                String site2 = fcstData2.getSite();
                return site1.compareTo(site2);
            }
        });
    return filterList;
    }

    public List<HomeQueryResult> timeLevelStatic(List<FcstData> fcstDataList, List<Integer> interval){
        List<HomeQueryResult> homeQueryResultList = new ArrayList<>();

        Map<String, List<FcstData>> map = fcstDataList.stream().collect(Collectors.groupingBy(
                FcstData::getSite));
        for (Map.Entry<String, List<FcstData>> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            List<FcstData> mapValue = entry.getValue();
            List<TimeLevelResult> timeLevelResultList = new ArrayList<>();
            for(Integer timeLevel : interval){
                TimeLevelResult timeLevelResult = findMaxAndMinByTimeLevel(mapValue,timeLevel);
                if(null != timeLevelResult){
                    timeLevelResultList.add(timeLevelResult);
                }
            }
            if(timeLevelResultList.size() == 0){
                return null;
            }
            HomeQueryResult homeQueryResult = new HomeQueryResult();
            homeQueryResult.setSite(mapKey);
            homeQueryResult.setTimeLevelResultList(timeLevelResultList);
            homeQueryResultList.add(homeQueryResult);
        }

        return homeQueryResultList;
    }

    /**
     * 仅用于逐时的数据, 此处fcstDataList需为同一个站点的
     * @param fcstDataList
     * @param timeLevel
     * @return
     */
    private TimeLevelResult findMaxAndMinByTimeLevel(List<FcstData> fcstDataList, Integer timeLevel){
        Map<String, List<FcstData>> map = fcstDataList.stream().collect(Collectors.groupingBy(
                FcstData::getSite));
        //如果不是唯一site,返回空值
            if(1 != map.size()){
                return null;
            }

        List<FcstData> sortedFcstDataList = fcstDataList.stream().sorted(Comparator.comparing(FcstData::getQueryTime))
                .collect(Collectors.toList());

        Date start = sortedFcstDataList.get(0).getQueryTime();
        //创建Calendar实例
        Calendar cal = Calendar.getInstance();
        //设置当前时间
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + timeLevel);
        //获取days天前的日期：此时start为当前系统时间，end为days天前时间
        Date staticEnd = cal.getTime();

        Calendar cal2 = Calendar.getInstance();
        //设置当前时间
        cal2.setTime(staticEnd);
        cal2.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);

        Date staticStart = cal2.getTime();

        List<FcstData> filterList = fcstDataList.stream().filter(s-> -1 < s.getQueryTime().compareTo(staticStart) && 1 > s.getQueryTime().compareTo(staticEnd)).collect(Collectors.toList());
        if(null == filterList || filterList.size() == 0){
            return null;
        }
//        String site = filterList.get(0).getSite();
        BigDecimal ybgMax = filterList.stream().max(Comparator.comparing(FcstData::getYbg)).get().getYbg();
        BigDecimal ybgMin = filterList.stream().min(Comparator.comparing(FcstData::getYbg)).get().getYbg();
        TimeLevelResult timeLevelResult = new TimeLevelResult(timeLevel,ybgMax,ybgMin);
        return timeLevelResult;
    }

}

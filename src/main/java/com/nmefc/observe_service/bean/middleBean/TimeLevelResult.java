package com.nmefc.observe_service.bean.middleBean;

import java.math.BigDecimal;

/**
 * @Description
 * @Date 2023/6/4 15:47
 * @Author Qu Yuan
 **/
public class TimeLevelResult {
    public Integer timeLevel;
    public BigDecimal max;
    public BigDecimal min;

    public TimeLevelResult(Integer timeLevel, BigDecimal max, BigDecimal min) {
        this.timeLevel = timeLevel;
        this.max = max;
        this.min = min;
    }

    public Integer getTimeLevel() {
        return timeLevel;
    }

    public void setTimeLevel(Integer timeLevel) {
        this.timeLevel = timeLevel;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }
}

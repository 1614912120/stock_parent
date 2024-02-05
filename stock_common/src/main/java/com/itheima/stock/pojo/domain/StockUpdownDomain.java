package com.itheima.stock.pojo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by itheima
 * @Date 2022/2/28
 * @Description 股票涨跌信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockUpdownDomain {

    @ExcelProperty(value = {"股票涨幅信息统计表","股票编码"},index = 0)
    private String code;

    @ExcelProperty(value = {"股票涨幅信息统计表","股票名称"},index = 1)
    private String name;

    @ExcelProperty(value = {"股票涨幅信息统计表","前收盘价格"},index = 2)
    private BigDecimal preClosePrice;

    @ExcelProperty(value = {"股票涨幅信息统计表","当前价格"},index= 3)
    private BigDecimal tradePrice;

    @ExcelProperty(value = {"股票涨幅信息统计表","涨跌"},index= 4)
    private BigDecimal increase;

    @ExcelProperty(value = {"股票涨幅信息统计表","涨幅"},index= 5)
    private BigDecimal upDown;

    @ExcelProperty(value = {"股票涨幅信息统计表","振幅"},index= 6)
    private BigDecimal amplitude;

    @ExcelProperty(value = {"股票涨幅信息统计表","交易总量"},index = 7)
    private Long tradeAmt;

    @ExcelProperty(value = {"股票涨幅信息统计表","交易总金额"},index = 8)
    private BigDecimal tradeVol;

    /**
     * 日期
     */
    @ExcelProperty(value = {"股票涨幅信息统计表","日期"},index = 9)
    @DateTimeFormat("yyy-MM-dd HH:mm")//easyExcel的注解-》excel
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
}
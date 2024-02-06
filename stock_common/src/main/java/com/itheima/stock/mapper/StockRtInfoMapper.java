package com.itheima.stock.mapper;

import com.itheima.stock.pojo.domain.Stock4EvrDayDomain;
import com.itheima.stock.pojo.domain.Stock4MinuteDomain;
import com.itheima.stock.pojo.domain.StockUpdownDomain;
import com.itheima.stock.pojo.entity.StockBlockRtInfo;
import com.itheima.stock.pojo.entity.StockRtInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author 123
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2024-02-01 16:21:30
* @Entity com.itheima.stock.pojo.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

    List<StockUpdownDomain> getNewestStockInfo(@Param("curDate") Date curDate);

    List<StockUpdownDomain> getNewestStockInfos(@Param("curDate") Date curDate);

    List<Map> getStockUpdownCount(@Param("openTime") Date openTime, @Param("curTime") Date curTime, @Param("flag") int i);

    List<StockUpdownDomain> getAllStockUpDownByTime(Date curDate);

    List<Map> getStockUpDownSectionByTime(Date curDate);

    List<Stock4MinuteDomain> getStockInfoByCodeAndDate(@Param("stockCode") String code, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Stock4EvrDayDomain> getStockInfo4EvrDay(@Param("stockCode") String stockCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<String> getStockInfo4EvrDayPart1(@Param("stockCode") String stockCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Stock4EvrDayDomain> getStockInfo4EvrDayPart2(@Param("stockCode") String stockCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("timeList1") List<String> timeList1);
    /**
     * 批量插入功能
     * @param stockRtInfoList
     */
    int insertBatch(List<StockRtInfo> stockRtInfoList);


}

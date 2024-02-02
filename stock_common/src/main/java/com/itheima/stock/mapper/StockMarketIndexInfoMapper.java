package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.StockMarketIndexInfo;

/**
* @author 123
* @description 针对表【stock_market_index_info(国内大盘数据详情表)】的数据库操作Mapper
* @createDate 2024-02-01 16:21:30
* @Entity com.itheima.stock.pojo.entity.StockMarketIndexInfo
*/
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

}

package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.StockOuterMarketIndexInfo;

/**
* @author 123
* @description 针对表【stock_outer_market_index_info(外盘详情信息表)】的数据库操作Mapper
* @createDate 2024-02-01 16:21:30
* @Entity com.itheima.stock.pojo.entity.StockOuterMarketIndexInfo
*/
public interface StockOuterMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockOuterMarketIndexInfo record);

    int insertSelective(StockOuterMarketIndexInfo record);

    StockOuterMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockOuterMarketIndexInfo record);

    int updateByPrimaryKey(StockOuterMarketIndexInfo record);

}

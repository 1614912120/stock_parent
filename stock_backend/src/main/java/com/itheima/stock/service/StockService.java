package com.itheima.stock.service;

import com.itheima.stock.pojo.domain.*;
import com.itheima.stock.vo.resp.PageResult;
import com.itheima.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * ClassName: StockService
 * Package: com.itheima.stock.service
 * Description:
 *
 * @Author R
 * @Create 2024/2/3 11:50
 * @Version 1.0
 */
public interface StockService {
    R<List<InnerMarketDomain>> getInnerMarketInfo();

    R<List<StockBlockDomain>> sectorAllLimit();

    /**
     * 分页查询股票最新数据，并按照涨幅排序查询
     * @param page
     * @param pageSize
     * @return
     */
    R<PageResult> getStockPageInfo(Integer page, Integer pageSize);

    R<List<StockUpdownDomain>> getStockPageInfos();

    /**
     *
     * @return
     */
    R<Map> getStockUpdownCount();

    void stockExport(HttpServletResponse response, Integer page, Integer pageSize);


    R<Map> stockTradeVol4InnerMarket();

    R<Map> stockUpDownScopeCount();

    R<List<Stock4MinuteDomain>> stockScreenTimeSharing(String code);

    R<List<Stock4EvrDayDomain>> stockCreenDkLine(String stockCode);
}

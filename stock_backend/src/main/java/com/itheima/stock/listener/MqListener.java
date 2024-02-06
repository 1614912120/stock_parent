package com.itheima.stock.listener;

import com.github.benmanes.caffeine.cache.Cache;
import com.itheima.stock.service.StockService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: MqListener
 * Package: com.itheima.stock.listener
 * Description:
 *
 * @Author R
 * @Create 2024/2/6 10:48
 * @Version 1.0
 */
@Slf4j
@Component
public class MqListener {
    @Autowired
    private Cache<String,Object> caffeineCache;
    @Autowired
    private StockService stockService;
    @RabbitListener(queues = "innerMarketQueue")
    public void acceptInnerMarketInfo(Date date) {
        //统计当前和发送时间点差值 超过1分钟 报警
        long diffTime = DateTime.now().getMillis() - new DateTime(date).getMillis();
        if(diffTime>60000) {
            log.error("采集国内大盘时间点：{},同步超时：{}ms",new DateTime(date).toString("yyyy-MM-dd HH:mm:ss"),diffTime);
        }
        //更新缓存
        //将缓存删除 更新缓存
        caffeineCache.invalidate("innerMarketKey");
        stockService.getInnerMarketInfo();
    }

}

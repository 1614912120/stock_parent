package com.itheima.stock;

import com.google.common.collect.Lists;
import com.itheima.stock.mapper.StockBusinessMapper;
import com.itheima.stock.pojo.Account;
import com.itheima.stock.service.StockTimerTaskService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UTFDataFormatException;
import java.util.List;
import java.util.Map;

/**
 * ClassName: test
 * Package: com.itheima.stock
 * Description:
 *
 * @Author R
 * @Create 2024/2/5 17:17
 * @Version 1.0
 */
@SpringBootTest
public class test {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test01() {
        String url="http://localhost:6666/account/getByUserNameAndAddress?userName=itheima&address=shanghai";
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        HttpHeaders headers = result.getHeaders();
        System.out.println(headers);
        int statusCode = result.getStatusCodeValue();
        System.out.println(statusCode);
        //响应数据
        String respData = result.getBody();
        System.out.println(respData);
    }

    @Test
    public void test02() {
        String url="http://localhost:6666/account/getByUserNameAndAddress?userName=itheima&address=shanghai";
        Account account = restTemplate.getForObject(url, Account.class);
        System.out.println(account);
    }

    /**
     * 请求头设置参数，访问指定接口
     */
    @Test
    public void test03() {
        String url="http://localhost:6666/account/getHeader";
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("userName","zhangsan");
        HttpEntity<Map> mapHttpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, mapHttpEntity, String.class);
        String body = exchange.getBody();
        System.out.println(body);
    }
    @Autowired
    private StockTimerTaskService stockTimerService;
    /**
     * 获取大盘数据
     */
    @Test
    public void test0134(){
        stockTimerService.getInnerMarketInfo();
    }

    @Autowired
    private StockBusinessMapper stockBusinessMapper;
    @Test
    public void test02ere(){
        List<String> stockIds = stockBusinessMapper.getStockIds();
        System.out.println(stockIds);
        List<List<String>> partition = Lists.partition(stockIds, 15);
        for (List<String> list : partition) {
            System.out.println(list);
        }
    }
}

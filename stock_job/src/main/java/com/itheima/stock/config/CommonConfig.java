package com.itheima.stock.config;

import com.itheima.stock.pojo.vo.StockInfoConfig;

import com.itheima.stock.utils.IdWorker;
import com.itheima.stock.utils.ParserStockInfoUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ClassName: CommonConfig
 * Package: com.itheima.stock.config
 * Description:
 *
 * @Author R
 * @Create 2024/2/2 9:33
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties({StockInfoConfig.class})
public class CommonConfig {
    /**
     * 定义密码加密 匹配bean
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public IdWorker idWorker(){
        //基于运维人员对机房和机器的编号规划自行约定
        return new IdWorker(1l,2l);
    }

    @Bean
    public ParserStockInfoUtil parserStockInfoUtil(IdWorker idWorker) {
        return new ParserStockInfoUtil(idWorker);
    }
}

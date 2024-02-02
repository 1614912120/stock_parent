package com.itheima.stock.config;

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
public class CommonConfig {
    /**
     * 定义密码加密 匹配bean
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

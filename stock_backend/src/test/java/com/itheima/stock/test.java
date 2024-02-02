package com.itheima.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.PriorityQueue;

/**
 * ClassName: test
 * Package: com.itheima.stock
 * Description:
 *
 * @Author R
 * @Create 2024/2/2 9:36
 * @Version 1.0
 */
@SpringBootTest
public class test {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void tests() {
        String pwd = "1234";
        String encode = passwordEncoder.encode(pwd);
        System.out.println(encode);

    }
}

package com.itheima.stock.controller;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.itheima.stock.controller
 * Description:
 *
 * @Author R
 * @Create 2024/2/1 16:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userName}")
    public SysUser getUserByUserName(@PathVariable("userName") String userName) {
        return userService.getUserByUserName(userName);
    }


    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo) {
       return  this.userService.login(vo);
    }


}

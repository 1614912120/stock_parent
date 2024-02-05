package com.itheima.stock.service;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;

import java.util.Map;

/**
 * @author by itheima
 * @Date 2022/6/29
 * @Description 定义用户服务接口
 */
public interface UserService {

    /**
     * 根据用户查询用户信息
     * @param userName 用户名称
     * @return
     */
    SysUser getUserByUserName(String userName);

    R<LoginRespVo> login(LoginReqVo vo);

    R<Map> getCaptchaCode();

}
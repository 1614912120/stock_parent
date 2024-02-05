package com.itheima.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.itheima.stock.constant.StockConstant;
import com.itheima.stock.mapper.SysUserMapper;
import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.utils.IdWorker;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import com.sun.glass.ui.View;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserServiceImpl
 * Package: com.itheima.stock.service.impl
 * Description:
 *
 * @Author R
 * @Create 2024/2/1 16:50
 * @Version 1.0
 */
//@Service("userService")
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private SysUserMapper sysUserMapper;
//    @Override
//    public SysUser getUserByUserName(String userName) {
//        SysUser user = sysUserMapper.findByUserName(userName);
//        return user;
//    }
//}

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 分布式环境保证生成id唯一
     */
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据用户名称查询用户信息
     * @param userName 用户名称
     * @return
     */
    @Override
    public SysUser getUserByUserName(String userName) {
        SysUser user=sysUserMapper.findByUserName(userName);
        return user;
    }

    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        //判断参数是否合法
        if(vo == null || StringUtils.isBlank(vo.getUsername())
        || StringUtils.isBlank(vo.getPassword())) {
            return R.error(ResponseCode.DATA_ERROR);
        }
        //2.校验验证码和sessionId是否有效
        if (StringUtils.isBlank(vo.getCode()) || StringUtils.isBlank(vo.getSessionId())){
            return R.error(ResponseCode.DATA_ERROR);
        }
        //从redia获取缓存得验证码
        String rcode = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + vo.getSessionId());
        if(StringUtils.isBlank(rcode) || !rcode.equalsIgnoreCase(vo.getCode())){
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }
        //根据用户名数据库查询
        SysUser user = sysUserMapper.findByUserName(vo.getUsername());
        if(user == null) {
            return R.error(ResponseCode.ACCOUNT_EXISTS_ERROR);
        }
        //存在 根据密码器匹配输入得密码
        if(!passwordEncoder.matches(vo.getPassword(),user.getPassword())){
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        LoginRespVo loginRespVo = new LoginRespVo();
        BeanUtils.copyProperties(user,loginRespVo);
        return R.ok(loginRespVo);
    }

    @Override
    public R<Map> getCaptchaCode() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        captcha.setBackground(Color.lightGray);
        //获取图片中的验证码，默认生成的校验码包含文字和数字，长度为4
        String checkCode = captcha.getCode();
        log.info("生成校验码:{}",checkCode);
        String imageData = captcha.getImageBase64();
        //生成sessionId
        String sessionId = String.valueOf(idWorker.nextId());
        //将sessionId和校验码保存在redis下，并设置缓存中数据存活时间一分钟
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX+sessionId,checkCode,5, TimeUnit.MINUTES);
        HashMap<String, String> data = new HashMap<>();
        data.put("imageData",imageData);
        data.put("sessionId",sessionId);
        return R.ok(data);
    }
}
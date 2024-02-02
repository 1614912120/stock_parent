package com.itheima.stock.service.impl;

import com.itheima.stock.mapper.SysUserMapper;
import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
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
        //根据用户名数据库查询
        SysUser user = sysUserMapper.findByUserName(vo.getUsername());
        if(user == null) {
            return R.error(ResponseCode.ACCOUNT_EXISTS_ERROR);
        }
        //根据密码器匹配输入得密码
        if(!passwordEncoder.matches(vo.getPassword(),user.getPassword())){
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        LoginRespVo loginRespVo = new LoginRespVo();
        BeanUtils.copyProperties(user,loginRespVo);
        return R.ok(loginRespVo);
    }
}
package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.SysLog;

/**
* @author 123
* @description 针对表【sys_log(系统日志)】的数据库操作Mapper
* @createDate 2024-02-01 16:21:30
* @Entity com.itheima.stock.pojo.entity.SysLog
*/
public interface SysLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

}

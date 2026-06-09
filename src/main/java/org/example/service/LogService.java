package org.example.service;

import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;

public interface LogService {

    /**
     * 分页查询操作日志
     * @param page
     * @param pageSize
     * @return
     */
    PageResult<OperateLog> pageQuery(Integer page, Integer pageSize);
}

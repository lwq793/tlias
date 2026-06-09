package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 分页查询操作日志
     * @param page
     * @param pageSize
     * @return
     */
    public PageResult<OperateLog> pageQuery(Integer page, Integer pageSize) {
        // 使用PageHelper设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行查询（按操作时间倒序）
        List<OperateLog> logList = operateLogMapper.selectOrderByTime();
        // 返回分页结果
        Page<OperateLog> p = (Page<OperateLog>) logList;

        return new PageResult<>(p.getTotal(), p.getResult());
    }
}

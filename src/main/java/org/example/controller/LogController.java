package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 分页查询操作日志
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询操作日志：page={}, pageSize={}", page, pageSize);
        PageResult<OperateLog> pageResult = logService.pageQuery(page, pageSize);
        return Result.success(pageResult);
    }
}

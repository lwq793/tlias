package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级管理
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result clazzPage(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询班级：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.clazzPage(clazzQueryParam);
        return Result.success(pageResult);


    }

    @PostMapping
    @Log
    public Result save(@RequestBody Clazz clazz){
        log.info("新增班级: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }


    @DeleteMapping("/{id}")
    @Log
    public Result delete(@PathVariable Integer id){
        log.info("删除id为{}的班级", id);
        clazzService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询班级：{}", id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result clazzList(){
        log.info("查询所有班级名称");
        List<Clazz> list = clazzService.clazzList();
        return Result.success(list);
    }


}

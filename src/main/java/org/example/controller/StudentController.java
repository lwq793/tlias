package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学员管理
 */
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result studentPage(StudentQueryParam studentQueryParam) {
        log.info("分页查询学员信息：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.studentPage(studentQueryParam);
        return Result.success(pageResult);

    }

    @PostMapping
    @Log
    public Result studentAdd(@RequestBody Student student) {
        log.info("新增学生：{}", student);
        studentService.studentAdd(student);
        return Result.success();
    }


    @DeleteMapping("/{ids}")
    @Log
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除id为{}的学员", ids);
        studentService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){

        log.info("根据id查询学员：{}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }


    @PutMapping
    @Log
    public Result update(@RequestBody Student student){
        log.info("修改学员信息：{}", student);
        studentService.update(student);
        return Result.success();

    }

    @PutMapping("/violation/{id}/{score}")
    @Log
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("将id为{}的学员违纪扣分增加{}", id, score);
        studentService.violation(id,score);
        return Result.success();
    }

}

package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzOption;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     * @return
     */

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计学员学历信息
     * @return
     */
    @GetMapping("/studentDegreeData")
    public Result getStuDegreeData(){
        log.info("统计学员学历信息");
        List<Map<String, Object>> degreeList = reportService.getStuDegreeData();
        return Result.success(degreeList);
    }

    /**
     * 统计各班级学员人数
     * @return
     */
    @GetMapping("/studentCountData")
    public Result getStuCountData(){
        log.info("统计各班级学员人数");
        ClazzOption clazzOption = reportService.getStuCountData();
        return Result.success(clazzOption);
    }
}

package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzOption;
import org.example.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    //统计员工职位信息
    JobOption getEmpJobData();

    //统计员工性别信息
    List<Map<String, Object>> getEmpGenderData();

    //统计学员学历信息
    List<Map<String, Object>> getStuDegreeData();

    //统计班级学员人数
    ClazzOption getStuCountData();
}

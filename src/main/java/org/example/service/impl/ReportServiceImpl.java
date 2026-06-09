package org.example.service.impl;

import org.example.mapper.EmpMappere;
import org.example.mapper.StudentMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzOption;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMappere empMappere;

    @Autowired
    private StudentMapper studentMappere;

    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口获取统计数据
        List<Map<String, Object>> list = empMappere.countEmpJobData();

        //2.组装结果并返回
        List<Object> jobList =
                list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList =
                list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);

    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {

        return empMappere.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return studentMappere.countStuDegreeData();
    }

    @Override
    public ClazzOption getStuCountData() {
        //1.调用mapper接口获取统计数据
        List<Map<String, Object>> list = studentMappere.countStuNumData();

        //2.组装结果并返回
        List<Object> clazzList =
                list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList =
                list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new ClazzOption(clazzList, dataList);
    }
}

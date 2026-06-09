package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {


    //分页查询
    PageResult<Clazz> clazzPage(ClazzQueryParam clazzQueryParam);

    //新增班级
    void save(Clazz clazz);

    //删除班级
    void delete(Integer id);

    //根据id查询班级
    Clazz getInfo(Integer id);

    //修改班级信息
    void update(Clazz clazz);

    //查询所有班级名称
    List<Clazz> clazzList();
}

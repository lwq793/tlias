package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    /**
     * 学员分页查询
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> studentPage(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student
     */
    void studentAdd(Student student);

    /**
     * 批量删除学员
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询学员
     * @param id
     * @return
     */
    Student getInfo(Integer id);

    /**
     * 修改学员信息
     * @param student
     */
    void update(Student student);

    /**
     * 违纪处理
     * @param id
     * @param score
     */
    void violation(Integer id, Integer score);
}

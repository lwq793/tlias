package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门数据
     *
     */
    List<Dept> findAll();



    /**
     * 按id删除部门
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     *
     */
    void add(Dept dept);

    /**
     * 根据id查询部门数据
     *
     */
    Dept getById(Integer id);

    /**
     * 修改部门数据
     */
    void update(Dept dept);
}

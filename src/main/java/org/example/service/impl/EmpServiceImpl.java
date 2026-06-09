package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMappere;
import org.example.pojo.*;
import org.example.service.EmpService;
import org.example.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMappere empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    /**
     * 原始分页查询
     * @return
     */

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//
//        PageResult<Emp> pageResult = new PageResult<>();
//        pageResult.setTotal(empMappere.count());
//        Integer start = (page - 1) * pageSize;
//        pageResult.setRows(empMappere.page(start, pageSize));
//        return pageResult;
//    }


    //------------------pageHelper实现分页-----------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1.设置分页参数
//        PageHelper.startPage(page, pageSize);
//
//        //2.执行查询
//        List<Emp> empList = empMapper.page(name, gender, begin, end);
//
//        //3.解析查询结果，并封装
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList = empMapper.page(empQueryParam);

        //3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    @Transactional(rollbackFor = {Exception.class})//事务管理 - 默认出现运行时异常RuntimeException才会回滚
    @Override
    public void save(Emp emp) {

        //1.员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //2.员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，为empId赋值
            for(EmpExpr expr : exprList){
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工信息
        empMapper.deleteByIds(ids);
        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.修改员工工作经历信息
        //2.1 先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //2.2 再添加
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            for(EmpExpr expr : exprList){
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    //查询所有员工姓名
    @Override
    public List<Emp> list() {
        return empMapper.list();
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper，根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //2.判断是否存在这个员工，如果存在，组装登陆成功信息
        if(e != null){
            log.info("登录成功，员工信息：{}", e);
            //生成Jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        //3.不存在，返回null
        return null;

    }

}

package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.EmpExpr;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量保存员工的工作经历信息
     * @param exprList
     */
//    @Insert("insert into emp_expr(emp_id, begin, end, company, job) values(#{})")
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id批量删除对应的员工经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}

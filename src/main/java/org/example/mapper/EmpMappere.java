package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMappere {


    //---------------------------原始分页查询--------------------------

    /**
     *查询总记录数
     *
     */
//    @Select("select count(*) from emp e left join  dept d on e.dept_id = d.id ")
//    public Long count();

    /**
     * 分页查询
     */
//    @Select("select e.*, d.name deptName from emp e left join  dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    //---------------------pagehelper分页查询--------------------

//    @Select("select e.*, d.name from emp e left join  dept d on e.dept_id = d.id " +
//            "where e.name like '%#{name}%' and e.gender = #{gender} and e.entry_date between '#{begin}' and '#{end}'" +
//            "order by e.update_time desc;")
//    public List<Emp> page(String name, Integer gender, LocalDate begin, LocalDate end);

    List<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键 --主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "VALUES(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据id批量删除员工基本信息
     * @param ids
     */

    void deleteByIds(List<Integer> ids);


    /**
     * 根据id查询员工信息以及员工工作经历信息
     */
    Emp getById(Integer id);


    /**
     * 修改员工
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 员工职位数据统计
     * @return
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();


    /**
     * 员工性别数据统计
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询所有员工姓名
     * @return
     */
    @Select("select id, name from emp")
    List<Emp> list();

    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return
     */
    @Select("select id, username, name from emp " +
            "where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}

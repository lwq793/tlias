package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 学员分页查询
     * @param studentQueryParam
     * @return
     */
    List<Student> studentPage(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student
     */
    void insert(Student student);

    /**
     * 根据id批量删除学员
     * @param ids
     */
    void deleteById(List<Integer> ids);

    /**
     * 根据id查询学员
     * @param id
     * @return
     */
    Student selectById(Integer id);

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

    /**
     * 统计学员学历信息
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countStuDegreeData();

    /**
     * 统计各班级学员人数
     * @return
     */
    @MapKey("pos")
    List<Map<String, Object>> countStuNumData();
}

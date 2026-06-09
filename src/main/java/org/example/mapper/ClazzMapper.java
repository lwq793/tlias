package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {


    //班级分页查询
    List<Clazz> clazzPage(ClazzQueryParam clazzQueryParam);

    //新增班级
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time)" +
            " VALUES(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    //根据id删除班级
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    //根据id查询班级
    @Select("select * from clazz where id = #{id}")
    Clazz selectById(Integer id);

    //修改班级信息
    void update(Clazz clazz);

    //查询所有班级名称
    @Select("select id, name from clazz")
    List<Clazz> clazzList();
}

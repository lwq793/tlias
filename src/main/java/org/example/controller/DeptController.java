package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;


    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //method: 指定请求方式
    @GetMapping
    public Result list(){
        //System.out.println("查询全部部门信息");
        log.info("查询全部部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    //删除部门 - 方式一 通过HttpServletRequest 获取请求参数
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据ID删除部门：" + id);
//        return Result.success();
//    }

//    //删除部门 - 方式二 @RequestParam注解 参数在请求时必须传递，否则会报错
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
//        System.out.println("根据ID删除部门：" + deptId);
//        return Result.success();
//    }

    //删除部门 - 方式三 @RequestParam注解(前端传递的请求参数与形参列表一致)
    @DeleteMapping
    @Log
    public Result delete(Integer id) {
        //System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result add(@RequestBody Dept dept) {
        //System.out.println("新增部门：" + dept);
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门信息：" + id);
        log.info("根据ID查询部门信息: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Dept dept) {
        //System.out.println("将id为" + dept.getId() + "的部门名称修改为："  + dept.getName());
        log.info("将id为{}的部门名称修改为{}", dept.getId(), dept.getName());
        deptService.update(dept);
        return Result.success();
    }
}

package com.dzl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dzl.common.R;
import com.dzl.entity.Admin;
import com.dzl.entity.Student;
import com.dzl.entity.Teacher;
import com.dzl.entity.vo.User;
import com.dzl.service.AdminService;
import com.dzl.service.StudentService;
import com.dzl.service.TeacherService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dzl.common.JwtUtils;
import com.dzl.common.MD5;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin  //解决跨域
public class LoginController {
    @Autowired
    StudentService studentService;
    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    //login
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }
    //info
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
    @PostMapping("/user/login")
    public R adminLogin(@ApiParam(name = "user", value = "登陆对象", required = true)
            @RequestBody User user){
        System.out.println(user.toString());
        if (user.getRole()==1){
            QueryWrapper<Admin> adminWrapper = new QueryWrapper<>();
            adminWrapper.eq("admin_name",user.getUserName());
             Admin byId = adminService.getOne(adminWrapper);
            if (byId==null){
                return  R.error().message("用户名错误");
            }
            String mypasswd=user.getUserPwd();
            String passwd=byId.getPasswd();

            if (mypasswd.equals(passwd)){

                String token = JwtUtils.getJwtToken(byId.getAdminId(), byId.getAdminName(),1);
                return R.ok().message("登陆成功").data("token", token);
            }else {
                return R.error().message("登录失败");
            }
        }else if (user.getRole()==2){
            QueryWrapper<Teacher> teacherWrapper = new QueryWrapper<>();
            teacherWrapper.eq("teacher_name",user.getUserName());
             Teacher byId = teacherService.getOne(teacherWrapper);
             if (byId==null){
                 return  R.error().message("用户名错误");
             }
            String mypasswd=user.getUserPwd();
            String passwd=byId.getPasswd();
            if (mypasswd.equals(passwd)){
                System.out.println(byId.toString());
                String token = JwtUtils.getJwtToken(byId.getTeacherId(), byId.getTeacherName(),2);
                System.out.println(token);
                return R.ok().message("登陆成功").data("token", token);
            }else {
                return R.error().message("登录失败");
            }
        }else if (user.getRole()==3){
            QueryWrapper<Student> studentWrapper = new QueryWrapper<>();
            studentWrapper.eq("stu_name",user.getUserName());
             Student byId = studentService.getOne(studentWrapper);
            if (byId==null){
                return  R.error().message("用户名错误");
            }
            String mypasswd=user.getUserPwd();
            String passwd=byId.getPasswd();
            if (mypasswd.equals(passwd)){
                String token = JwtUtils.getJwtToken(byId.getStuId(), byId.getStuName(),3);
                System.out.println(token);

                return R.ok().message("登陆成功").data("token", token);
            }else {
                return R.error().message("登录失败");
            }
        }
        return R.error().message("角色选择错误");
    }

    @PostMapping("/admin/register")
    public R adminRegister(@RequestBody Admin admin){
        if (admin.getCode() != 999) {
            return R.error().message("注册码错误");

        }
        adminService.save(admin);
        return R.ok();
    }
    @PostMapping("/student/register")
    public R studentRegister(@RequestBody Student student){
        System.out.println(student.toString());
        studentService.save(student);
        return R.ok();
    }
    @PostMapping("/teacher/register")
    public R teacherRegister(@RequestBody Teacher teacher){
        System.out.println(teacher.toString());
        teacherService.save(teacher);
        return R.ok();
    }

    //根据tocken获取用户信息
    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request) {
        //调用jwt的工具类方法,根据request对象获取头信息,返回用户id
        Integer memberId = JwtUtils.getMemberIdByJwtToken(request);
         int role = JwtUtils.getRoleByJwtToken(request);
        //根据id查询用户信息
        System.out.println("用户id为"+memberId);
            if (role==2){
        Teacher member = this.teacherService.getById(memberId);
                System.out.println(member.toString());
                return R.ok().data("userInfo", member).data("userrole",role);
            }else if (role==3){
                Student member=this.studentService.getById(memberId);
            System.out.println(member.toString());
                System.out.println(role+"角色");
            return R.ok().data("userInfo", member).data("userrole",role);
        }else {
                return R.error();
            }

    }
}

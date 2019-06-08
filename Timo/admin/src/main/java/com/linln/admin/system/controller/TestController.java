package com.linln.admin.system.controller;


import com.linln.modules.system.domain.User;
import com.linln.modules.system.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户接口", description = "提供用户相关的 Rest API")
public class TestController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    /**
     *  返回json数据
     */
    @GetMapping("/json")
    @ApiOperation("测试接口")
    public Map< String, Object> jsonLogin(){
        User user = userServiceImpl.getById(1L);
        System.out.println( user.getNickname());
        Map<String, Object> data = new HashMap<>();
        data.put("message",1);
        data.put("user",user);

        return data;
      //  System.out.println(ResultVoUtil.success("请求成功"));
        // return user;
    }
    @ApiOperation("查询用户信息")
    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") int id) {
        return new User();
    }
}

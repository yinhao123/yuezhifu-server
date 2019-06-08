package com.linln.api;

import com.linln.modules.system.domain.User;
import com.linln.modules.system.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@Api(tags ="Api接口")
public class DemoController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/test")
    @ApiOperation(value ="演示接口")
    public User Demo() {
        System.out.println("111111111111");
        User user = userService.getById(1L);
        System.out.println(user.getNickname());
      //  Assert.isNull(user.getNickname(),"超级管理员");
        return user;
    }
}

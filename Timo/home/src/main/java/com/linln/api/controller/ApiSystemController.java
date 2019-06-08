package com.linln.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = "系统设置接口")
public class ApiSystemController {
    @ApiOperation("查询用户系统设置")
    @GetMapping("/system/{id}")
    public void ApiSystemFind(@PathVariable("id") long id){

    }

    @ApiOperation("更新用户设置")
    @PostMapping("/system/{id}")
    public void ApiSystemUpdate()throws Exception{

    }
}

package com.linln.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(tags = "系统设置接口")
public class ApiSystemController {
    @ApiOperation("查询用户系统设置")
    @GetMapping("/system/{id}")
    public void ApiSystemFind(@PathVariable("id") long id){

    }
}

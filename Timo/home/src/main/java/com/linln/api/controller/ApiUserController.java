package com.linln.api.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.system.domain.User;
import com.linln.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户信息管理")
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息")
    @GetMapping("/user/{name}")
    public ResultVo index(@PathVariable("name") String name)
    {
       User user = userService.getByName(name);
       return ResultVoUtil.success("success",user);
    }



}

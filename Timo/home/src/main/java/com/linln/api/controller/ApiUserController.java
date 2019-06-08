package com.linln.api.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.system.domain.User;
import com.linln.modules.system.service.UserService;
import com.linln.modules.system.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户信息接口")
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{id}")
    @ApiOperation(value ="用户信息查询")
    public ResultVo getUerInfo(@PathVariable("id") long id) throws Exception{
        User user = userService.getById(id);
        //ResultVoUtil.success("success",user);
        return  ResultVoUtil.success("success",user);
    }

    @PostMapping("/user/{id}")
    @ApiOperation(value ="修改用户信息")
    public String updateUser(User user) throws Exception{
        userService.save(user);
        return "ok";
    }
}

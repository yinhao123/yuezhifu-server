package com.linln.api.controller;
import	java.sql.Ref;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.order.domain.Orders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.linln.modules.order.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags ="订单管理")
@RequestMapping("/api")
public class ApiOrderController {

    @Autowired
    private OrdersService ordersService;
    /**
     *订单查询
      */
    @GetMapping("/order/list")
    @ApiOperation("订单列表查询")
    public ResultVo index(Orders orders) {
        List<Orders> od =  ordersService.getBySellerId(orders.getUserId());
        return  ResultVoUtil.success("success",od);
    }
    /**
     * 订单详情
     */
    @GetMapping("/order/{id}")
    @ApiOperation("订单详情")
    public ResultVo orderInfo(@PathVariable("id") Long id)
    {
       Orders or = ordersService.getById(id);
       return ResultVoUtil.success("success",or);
    }
    /**
     * 生成订单
     */
    @PostMapping("/order")
    @ApiOperation("生成订单")
    public ResultVo orderAdd(Orders orders){
     Orders or  =   ordersService.save(orders);
     return ResultVoUtil.success("success",or);
    }
    /**
     * 删除订单
     */
    @DeleteMapping("/order")
    @ApiOperation("删除订单，普通用户不让删除订单")
    public void orderDelete()
    {

    }


}

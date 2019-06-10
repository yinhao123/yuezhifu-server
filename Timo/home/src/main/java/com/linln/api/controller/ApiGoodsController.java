package com.linln.api.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.goods.domain.Shopgoods;
import com.linln.modules.goods.service.ShopgoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags ="商品管理")
@RequestMapping("/api")
public class ApiGoodsController {
    @Autowired
    private ShopgoodsService shopgoodsService;

    @ApiOperation("商品列表")
    @GetMapping("/goods/list")
    public ResultVo index(Shopgoods shopgoods){

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", match -> match.contains());

        // 获取数据列表
        Example<Shopgoods> example = Example.of(shopgoods, matcher);
        Page<Shopgoods> list = shopgoodsService.getPageList(example);

        return ResultVoUtil.success("success",list);
    }

    @ApiOperation("获取单个商品接口")
    @GetMapping("/goods/{id}")
    public ResultVo goodsShow(@PathVariable("id") Long id ){
        Shopgoods shopgoods = shopgoodsService.getById(id);
        return ResultVoUtil.success("success",shopgoods);
    }

}

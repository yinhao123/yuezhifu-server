package com.linln.admin.shop.controller;

import com.linln.admin.shop.domain.Goods;
import com.linln.admin.shop.service.GoodsService;
import com.linln.admin.shop.validator.GoodsValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yinhao
 * @date 2019/06/05
 */
@Controller
@RequestMapping("/shop/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("shop:goods:index")
    public String index(Model model, Goods goods) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", match -> match.contains());

        // 获取数据列表
        Example<Goods> example = Example.of(goods, matcher);
        Page<Goods> list = goodsService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/shop/goods/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("shop:goods:add")
    public String toAdd() {
        return "/shop/goods/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("shop:goods:edit")
    public String toEdit(@PathVariable("id") Goods goods, Model model) {
        model.addAttribute("goods", goods);
        return "/shop/goods/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"shop:goods:add","shop:goods:edit"})
    @ResponseBody
    public ResultVo save(@Validated GoodsValid valid, Goods goods) {
        // 复制保留无需修改的数据
        if (goods.getId() != null) {
            Goods beGoods = goodsService.getById(goods.getId());
            EntityBeanUtil.copyProperties(beGoods, goods);
        }

        // 保存数据
        goodsService.save(goods);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("shop:goods:detail")
    public String toDetail(@PathVariable("id") Goods goods, Model model) {
        model.addAttribute("goods",goods);
        return "/shop/goods/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("shop:goods:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (goodsService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}
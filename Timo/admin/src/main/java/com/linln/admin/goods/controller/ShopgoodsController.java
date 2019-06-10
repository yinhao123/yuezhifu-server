package com.linln.admin.goods.controller;

import com.linln.admin.goods.validator.ShopgoodsValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.goods.domain.Shopgoods;
import com.linln.modules.goods.service.ShopgoodsService;
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
 * @author Yinhao
 * @date 2019/06/09
 */
@Controller
@RequestMapping("/goods/shopgoods")
public class ShopgoodsController {

    @Autowired
    private ShopgoodsService shopgoodsService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("goods:shopgoods:index")
    public String index(Model model, Shopgoods shopgoods) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", match -> match.contains());

        // 获取数据列表
        Example<Shopgoods> example = Example.of(shopgoods, matcher);
        Page<Shopgoods> list = shopgoodsService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/goods/shopgoods/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("goods:shopgoods:add")
    public String toAdd() {
        return "/goods/shopgoods/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("goods:shopgoods:edit")
    public String toEdit(@PathVariable("id") Shopgoods shopgoods, Model model) {
        model.addAttribute("shopgoods", shopgoods);
        return "/goods/shopgoods/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"goods:shopgoods:add","goods:shopgoods:edit"})
    @ResponseBody
    public ResultVo save(@Validated ShopgoodsValid valid, Shopgoods shopgoods) {
        // 复制保留无需修改的数据
        if (shopgoods.getId() != null) {
            Shopgoods beShopgoods = shopgoodsService.getById(shopgoods.getId());
            EntityBeanUtil.copyProperties(beShopgoods, shopgoods);
        }

        // 保存数据
        shopgoodsService.save(shopgoods);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("goods:shopgoods:detail")
    public String toDetail(@PathVariable("id") Shopgoods shopgoods, Model model) {
        model.addAttribute("shopgoods",shopgoods);
        return "/goods/shopgoods/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("goods:shopgoods:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (shopgoodsService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}
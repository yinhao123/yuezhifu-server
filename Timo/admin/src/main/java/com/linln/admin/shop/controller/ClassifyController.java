package com.linln.admin.shop.controller;

import com.linln.admin.shop.domain.Classify;
import com.linln.admin.shop.service.ClassifyService;
import com.linln.admin.shop.validator.ClassifyValid;
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
@RequestMapping("/shop/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("shop:classify:index")
    public String index(Model model, Classify classify) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", match -> match.contains());

        // 获取数据列表
        Example<Classify> example = Example.of(classify, matcher);
        Page<Classify> list = classifyService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/shop/classify/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("shop:classify:add")
    public String toAdd() {
        return "/shop/classify/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("shop:classify:edit")
    public String toEdit(@PathVariable("id") Classify classify, Model model) {
        model.addAttribute("classify", classify);
        return "/shop/classify/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"shop:classify:add","shop:classify:edit"})
    @ResponseBody
    public ResultVo save(@Validated ClassifyValid valid, Classify classify) {
        // 复制保留无需修改的数据
        if (classify.getId() != null) {
            Classify beClassify = classifyService.getById(classify.getId());
            EntityBeanUtil.copyProperties(beClassify, classify);
        }

        // 保存数据
        classifyService.save(classify);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("shop:classify:detail")
    public String toDetail(@PathVariable("id") Classify classify, Model model) {
        model.addAttribute("classify",classify);
        return "/shop/classify/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("shop:classify:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (classifyService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}
package com.linln.admin.store.controller;

import com.linln.admin.store.validator.StoreManagerValid;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.EntityBeanUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.utils.StatusUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.store.domain.StoreManager;
import com.linln.modules.store.service.StoreManagerService;
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
@RequestMapping("/store/storeManager")
public class StoreManagerController {

    @Autowired
    private StoreManagerService storeManagerService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("store:storeManager:index")
    public String index(Model model, StoreManager storeManager) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", match -> match.contains());

        // 获取数据列表
        Example<StoreManager> example = Example.of(storeManager, matcher);
        Page<StoreManager> list = storeManagerService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/store/storeManager/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("store:storeManager:add")
    public String toAdd() {
        return "/store/storeManager/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("store:storeManager:edit")
    public String toEdit(@PathVariable("id") StoreManager storeManager, Model model) {
        model.addAttribute("storeManager", storeManager);
        return "/store/storeManager/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping({"/add","/edit"})
    @RequiresPermissions({"store:storeManager:add","store:storeManager:edit"})
    @ResponseBody
    public ResultVo save(@Validated StoreManagerValid valid, StoreManager storeManager) {
        // 复制保留无需修改的数据
        if (storeManager.getId() != null) {
            StoreManager beStoreManager = storeManagerService.getById(storeManager.getId());
            EntityBeanUtil.copyProperties(beStoreManager, storeManager);
        }

        // 保存数据
        storeManagerService.save(storeManager);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("store:storeManager:detail")
    public String toDetail(@PathVariable("id") StoreManager storeManager, Model model) {
        model.addAttribute("storeManager",storeManager);
        return "/store/storeManager/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("store:storeManager:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (storeManagerService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}
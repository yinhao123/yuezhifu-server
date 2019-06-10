package com.linln.api.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.jwt.annotation.JwtPermissions;
import com.linln.modules.store.domain.StoreManager;
import com.linln.modules.store.service.StoreManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags ="店铺设置")
@RequestMapping("/api")
public class ApiStoreController {
    @Autowired
    private StoreManagerService storeManagerService;

    @ApiOperation("查询店铺设置")
    @GetMapping("/store/settings/{id}")
  //  @JwtPermissions
    public ResultVo storeSettings(@PathVariable("id") Long id){
        StoreManager storeManager = storeManagerService.getBySeller(id);
      return    ResultVoUtil.success("success",storeManager);
    }
    @ApiOperation("更新店铺设置")
    @PostMapping("/sotre/settings/{id}")
    public ResultVo storeSettingsUpdate(){
        //storeManagerService.updateStatus();
        return ResultVoUtil.success();
    }
}

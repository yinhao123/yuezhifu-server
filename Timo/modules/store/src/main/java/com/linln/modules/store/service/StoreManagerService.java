package com.linln.modules.store.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.store.domain.StoreManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yinhao
 * @date 2019/06/09
 */
public interface StoreManagerService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<StoreManager> getPageList(Example<StoreManager> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    StoreManager getById(Long id);

    /**
     * 根据买家seller返回设置信息
     */
    StoreManager getBySeller(Long id);
    /**
     * 保存数据
     * @param storeManager 实体对象
     */
    StoreManager save(StoreManager storeManager);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}
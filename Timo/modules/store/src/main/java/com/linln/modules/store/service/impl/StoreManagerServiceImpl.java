package com.linln.modules.store.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.store.domain.StoreManager;
import com.linln.modules.store.repository.StoreManagerRepository;
import com.linln.modules.store.service.StoreManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yinhao
 * @date 2019/06/09
 */
@Service
public class StoreManagerServiceImpl implements StoreManagerService {

    @Autowired
    private StoreManagerRepository storeManagerRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public StoreManager getById(Long id) {
        return storeManagerRepository.findById(id).orElse(null);
    }

    @Override
    public StoreManager getBySeller(Long id) {

        return storeManagerRepository.findBySeller(id);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<StoreManager> getPageList(Example<StoreManager> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return storeManagerRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param storeManager 实体对象
     */
    @Override
    public StoreManager save(StoreManager storeManager) {
        return storeManagerRepository.save(storeManager);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return storeManagerRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}
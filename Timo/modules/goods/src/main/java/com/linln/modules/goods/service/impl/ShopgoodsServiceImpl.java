package com.linln.modules.goods.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.goods.domain.Shopgoods;
import com.linln.modules.goods.repository.ShopgoodsRepository;
import com.linln.modules.goods.service.ShopgoodsService;
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
public class ShopgoodsServiceImpl implements ShopgoodsService {

    @Autowired
    private ShopgoodsRepository shopgoodsRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Shopgoods getById(Long id) {
        return shopgoodsRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Shopgoods> getPageList(Example<Shopgoods> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return shopgoodsRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param shopgoods 实体对象
     */
    @Override
    public Shopgoods save(Shopgoods shopgoods) {
        return shopgoodsRepository.save(shopgoods);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return shopgoodsRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}
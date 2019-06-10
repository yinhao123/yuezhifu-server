package com.linln.modules.goods.service;

import com.linln.common.enums.StatusEnum;
import com.linln.modules.goods.domain.Shopgoods;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yinhao
 * @date 2019/06/09
 */
public interface ShopgoodsService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<Shopgoods> getPageList(Example<Shopgoods> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    Shopgoods getById(Long id);

    /**
     * 保存数据
     * @param shopgoods 实体对象
     */
    Shopgoods save(Shopgoods shopgoods);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}
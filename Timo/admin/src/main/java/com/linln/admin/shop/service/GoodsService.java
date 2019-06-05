package com.linln.admin.shop.service;

import com.linln.admin.shop.domain.Goods;
import com.linln.common.enums.StatusEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yinhao
 * @date 2019/06/05
 */
public interface GoodsService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<Goods> getPageList(Example<Goods> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    Goods getById(Long id);

    /**
     * 保存数据
     * @param goods 实体对象
     */
    Goods save(Goods goods);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}
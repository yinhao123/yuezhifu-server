package com.linln.admin.shop.service.impl;

import com.linln.admin.shop.domain.Order;
import com.linln.admin.shop.repository.OrderRepository;
import com.linln.admin.shop.service.OrderService;
import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yinhao
 * @date 2019/06/05
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Order getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Order> getPageList(Example<Order> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return orderRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param order 实体对象
     */
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return orderRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}
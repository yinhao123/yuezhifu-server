package com.linln.modules.order.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.enums.StatusEnum;
import com.linln.modules.order.domain.Orders;
import com.linln.modules.order.repository.OrdersRepository;
import com.linln.modules.order.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yinhao
 * @date 2019/06/08
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrdersService ordersService;


    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Orders getById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Orders> getBySellerId(Long sellerId) {
        //ordersRepository;
       // Page<Orders> list = ordersService.getPageList();
       // ordersRepository.findByUserId(sellerId);
       return ordersRepository.findByUserId(sellerId);
       // return ordersRepository.findByUserId(1L);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Orders> getPageList(Example<Orders> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return ordersRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param orders 实体对象
     */
    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return ordersRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}
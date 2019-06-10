package com.linln.modules.order.repository;

import com.linln.modules.order.domain.Orders;
import com.linln.modules.system.repository.BaseRepository;

import java.util.List;

/**
 * @author Yinhao
 * @date 2019/06/08
 */
public interface OrdersRepository extends BaseRepository<Orders, Long> {
    // 使用 distinct 关键字
    List<Orders> findByUserId(Long userId);
}
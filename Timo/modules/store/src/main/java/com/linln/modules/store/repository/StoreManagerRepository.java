package com.linln.modules.store.repository;

import com.linln.modules.store.domain.StoreManager;
import com.linln.modules.system.repository.BaseRepository;

/**
 * @author Yinhao
 * @date 2019/06/09
 */
public interface StoreManagerRepository extends BaseRepository<StoreManager, Long> {
    StoreManager findBySeller(Long Id);
}
package com.linln.admin.shop.service.impl;

import com.linln.admin.shop.domain.Classify;
import com.linln.admin.shop.repository.ClassifyRepository;
import com.linln.admin.shop.service.ClassifyService;
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
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyRepository classifyRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Classify getById(Long id) {
        return classifyRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Classify> getPageList(Example<Classify> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return classifyRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param classify 实体对象
     */
    @Override
    public Classify save(Classify classify) {
        return classifyRepository.save(classify);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return classifyRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}
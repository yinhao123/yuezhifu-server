package com.linln.admin.store.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author Yinhao
 * @date 2019/06/09
 */
@Data
public class StoreManagerValid implements Serializable {
    @NotEmpty(message = "店铺名称不能为空")
    private String title;
}
package com.linln.admin.goods.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author Yinhao
 * @date 2019/06/09
 */
@Data
public class ShopgoodsValid implements Serializable {
    @NotEmpty(message = "商品名称不能为空")
    private String title;
}
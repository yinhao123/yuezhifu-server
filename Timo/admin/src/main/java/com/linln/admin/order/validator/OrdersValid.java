package com.linln.admin.order.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author Yinhao
 * @date 2019/06/08
 */
@Data
public class OrdersValid implements Serializable {
    @NotEmpty(message = "商品名称不能为空")
    private String title;
}
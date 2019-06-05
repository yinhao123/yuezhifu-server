package com.linln.admin.shop.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author yinhao
 * @date 2019/06/05
 */
@Data
public class SupportValid implements Serializable {
    @NotEmpty(message = "标题不能为空")
    private String title;
}
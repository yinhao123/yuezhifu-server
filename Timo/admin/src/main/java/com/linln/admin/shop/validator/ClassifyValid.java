package com.linln.admin.shop.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author yinhao
 * @date 2019/06/05
 */
@Data
public class ClassifyValid implements Serializable {
    @NotEmpty(message = "分类名称不能为空")
    private String title;
}
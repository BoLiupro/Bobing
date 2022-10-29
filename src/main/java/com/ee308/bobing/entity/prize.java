package com.ee308.bobing.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Prize对象", description="")
public class prize implements Serializable {

    private static final long serialVersionUID = 1L;

    private String prize;

    private Integer quantity;

}

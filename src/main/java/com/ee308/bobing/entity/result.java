package com.ee308.bobing.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="Result对象", description="")
public class result implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer playerId;

    private Integer diceResult;

}

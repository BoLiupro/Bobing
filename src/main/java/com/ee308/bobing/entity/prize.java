package com.ee308.bobing.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(value = "prize_id", type = IdType.AUTO)
    private Integer prize_id;

    private String prize_level;

    private Integer quantity;

    private String prize;

    public prize(Integer prize_id, String prize_level, Integer quantity, String prize) {
    }
}

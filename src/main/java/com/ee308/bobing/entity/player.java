package com.ee308.bobing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Player对象", description="")
public class player implements Serializable {

    private static final long serialVersionUID = 1L;


    /*
    important!
    player_id range from 1 ~ player_num instead of 0 ~ player_num-1
    */
    @TableId(value = "player_id", type = IdType.AUTO)
    private Integer player_id;

    private String player_name;

    private String phone;




}

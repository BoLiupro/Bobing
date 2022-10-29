package com.ee308.bobing.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ee308.bobing.entity.player;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */

@Mapper
public interface PlayerMapper extends BaseMapper<player> {

}

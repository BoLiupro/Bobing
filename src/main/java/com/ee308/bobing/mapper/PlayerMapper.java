package com.ee308.bobing.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ee308.bobing.entity.player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */

@Mapper
@Repository
public interface PlayerMapper extends BaseMapper<player> {

    void insert(String player_name, String phone);

    @Update("truncate table player")
    void truncateTable();


}

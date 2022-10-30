package com.ee308.bobing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ee308.bobing.entity.result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */
@Mapper
public interface ResultMapper extends BaseMapper<result> {

    void insert(int player_id, String dice_result, String prize_result);

    @Update("truncate table result")
    void truncateTable();
}

package com.ee308.bobing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ee308.bobing.entity.prize;
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
public interface PrizeMapper extends BaseMapper<prize> {

    @Update("truncate table prize")
    void truncateTable();

    void insert(String prize_level, int quantity, String prize);

    void resetPrizeNum(int prize_id,int quantity);

}

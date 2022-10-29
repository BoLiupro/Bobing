package com.ee308.bobing.service.impl;

import com.ee308.bobing.entity.player;
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.service.Interface.PlayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, player> implements PlayerService {

}

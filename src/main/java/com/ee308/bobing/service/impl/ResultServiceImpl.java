package com.ee308.bobing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ee308.bobing.entity.Result;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.service.Interface.ResultService;
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
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultService {

}

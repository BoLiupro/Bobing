package com.ee308.bobing.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ee308.bobing.entity.player;
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.response.ResponseResult;
import com.ee308.bobing.service.impl.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */
@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    ResultMapper resultMapper;
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    PrizeMapper prizeMapper;

    @GetMapping("/getAll")
    public ResponseResult getResult(){
        ResultServiceImpl resultService =new ResultServiceImpl();
        List<Map<String,Object>> finalResultMaps= resultService.getAllResult();
        return ResponseResult.ok().data("all the result",finalResultMaps);
    }

}


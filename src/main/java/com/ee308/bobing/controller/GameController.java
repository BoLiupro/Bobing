package com.ee308.bobing.controller;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ee308.bobing.service.impl.PlayerServiceImpl;
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import com.ee308.bobing.entity.*;

@RestController
@RequestMapping("/game")
public class GameController {
    int startIndex;

    @Autowired
    ResultMapper resultMapper;

    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    PrizeMapper prizeMapper;


    @GetMapping("/setRound")
    public ResponseResult setRound(@RequestParam("roundNUm") int roundNum){
        PlayerServiceImpl playerService =new PlayerServiceImpl();
        playerService.setRoundNum(roundNum);
        return ResponseResult.ok().message("successfully set roundNum "+roundNum);
    }

    @ApiOperation("choose the person to start")
    @GetMapping("/chooseStart")//point a starter
    public ResponseResult chooseStart(@RequestParam("startIndex") int startIndex){
        this.startIndex=startIndex;
        return ResponseResult.ok().data("start_index",startIndex);
    }

    @ApiOperation("choose the person to start by random")
    @GetMapping("/chooseStartByRandom")//point a starter
    public ResponseResult chooseStartByRandom(){
        int num=playerMapper.selectList((Wrapper<player>) new QueryWrapper().gt("player_id",0)).size();
        Random r= new Random();
        this.startIndex=r.nextInt(num)+1;
        return ResponseResult.ok().data("start_index",this.startIndex);
    }

    @ApiOperation("core function, return result")
    @GetMapping("/throwDice")
    public ResponseResult throwDice(@RequestParam("player_id") int player_id){
        PlayerServiceImpl playerService = new PlayerServiceImpl();
        return playerService.getResult(player_id);
    }

}

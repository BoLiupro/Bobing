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
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import com.ee308.bobing.entity.*;
import com.ee308.bobing.service.impl.*;

import javax.naming.spi.ResolveResult;

@RestController
@RequestMapping("/game")
public class GameController {
    int startIndex;

    @Autowired
    ResultMapper resultMapper;

    @Autowired
    PlayerMapper playerMapper;

    @GetMapping("/chooseStart")//point a starter
    public ResponseResult chooseStart(@RequestParam("startIndex") int startIndex){
        this.startIndex=startIndex;
        return ResponseResult.ok().data("start_index",startIndex);
    }

    @GetMapping("/chooseStartByRandom")//point a starter
    public ResponseResult chooseStartByRandom(@RequestParam("startIndex") int startIndex){
        int num=playerMapper.selectList((Wrapper<player>) new QueryWrapper().gt("player_id",0)).size();
        Random r= new Random(num);
        this.startIndex=startIndex+1;
        return ResponseResult.ok().data("start_index",startIndex);
    }

    @GetMapping("/throwDice")
    public ResponseResult throwDice(@RequestParam("player_id") int player_id){
        result r= new result();
        Dice dice = new Dice();
        String dice_result;
        int n=0;
        for(int i=0;i<6;i++){
            n += Math.pow(10,i)*dice.throwDice();
            /*
            1~6 dice will from a 6 digit number
            representing the 6 times dices result
             */
        }
        dice_result= String.valueOf(n);
        String prize_result=Awards.awardsMatch1(dice_result);
        resultMapper.insert(player_id,dice_result,prize_result);
        r.setPlayer_id(player_id);r.setDice_result(dice_result);r.setPrize_result(prize_result);
        return ResponseResult.ok().data("result",r);
    }

}

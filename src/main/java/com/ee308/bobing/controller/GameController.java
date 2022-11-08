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
import com.baomidou.mybatisplus.extension.api.R;
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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.ee308.bobing.entity.*;
import com.ee308.bobing.service.impl.*;

@RestController
@RequestMapping("/game")
public class GameController {
    int startIndex;

    Round round =new Round();

    @Autowired
    ResultMapper resultMapper;

    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    PrizeMapper prizeMapper;

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
        minus(prize_result);
        ListIterator listIterator=prizeMapper.selectList((Wrapper<prize>) new QueryWrapper().gt("prize_id",0)).listIterator();
        List prize_Num = new ArrayList<>();
        while(listIterator.hasNext()){
            prize prize= (com.ee308.bobing.entity.prize) listIterator.next();
            prize_Num.add(prize.getQuantity());
        }
        int num=playerMapper.selectList((Wrapper<player>) new QueryWrapper().gt("player_id",0)).size();
        return ResponseResult.ok().data("round",round.getRound(num)).
                data("player",playerMapper.selectMaps((Wrapper<player>) new QueryWrapper().eq("player_id",player_id))).
                data("result",r).
                data("prize",prize_Num);
    }

    public void minus(String prize_level){
        if(prize_level.equals("none"))
            return;
        QueryWrapper<prize> wrapper = new QueryWrapper();
        wrapper.eq("prize_level", prize_level);
        prize p =prizeMapper.selectOne(wrapper);
        p.setQuantity(p.getQuantity()-1);
        prizeMapper.update(p,wrapper);
    }

}

package com.ee308.bobing.controller;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */

import com.ee308.bobing.mapper.ResultMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import com.ee308.bobing.entity.*;
import com.ee308.bobing.service.impl.*;

@RestController
@RequestMapping("/game")
public class GameController {
    int startIndex;

    ResultMapper resultMapper;

    @GetMapping("/chooseStart")//point a starter
    public void chooseStart(@RequestParam("startIndex") int startIndex){
        this.startIndex=startIndex;
    }

    @GetMapping("/chooseStartByRandom")//point a starter
    public void chooseStartByRandom(@RequestParam("startIndex") int startIndex){
        int num=players.playerList.size();
        Random r= new Random(num);
        this.startIndex=startIndex+1;
    }

    @GetMapping("/throwDice")
    public result throwDice(@RequestParam("player_id") int player_id){
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
        String prize_result="";
        resultMapper.insert(player_id,dice_result,prize_result);
        r.setPlayerId(player_id);r.setDiceResult(dice_result);r.setPrizeResult(prize_result);
        return r;
    }



}

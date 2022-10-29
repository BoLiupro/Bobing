package com.ee308.bobing.controller;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */

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




}

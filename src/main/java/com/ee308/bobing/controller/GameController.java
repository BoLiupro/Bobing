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
import org.springframework.web.bind.annotation.RequestParam;

public class GameController {
    int startIndex;

    @GetMapping("/game/chooseStart")
    public void chooseStart(@RequestParam("startIndex") int startIndex){
        this.startIndex=startIndex;
    }


}

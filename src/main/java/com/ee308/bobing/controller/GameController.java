package com.ee308.bobing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class GameController {
    int startIndex;

    @GetMapping("/game/chooseStart")
    public void chooseStart(@RequestParam("startIndex") int startIndex){
        this.startIndex=startIndex;
    }


}

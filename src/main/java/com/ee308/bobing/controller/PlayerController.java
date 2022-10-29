package com.ee308.bobing.controller;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */

import com.ee308.bobing.entity.player;
import com.ee308.bobing.entity.players;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class PlayerController {

    players players=new players();
    /*
    web page to set player number
     */
    @GetMapping("/playerSetting")
    public players playersSet(@RequestParam("playerNum") int playerNum) {
        players.playerList=new ArrayList<>(playerNum);
        return players;
    }

    /*
    web page to set every player information
     */
    @GetMapping("/playerSetting/{playerName}")
    public void playerSet(@PathVariable("playerName") String playerName, @RequestParam("phone") String phone){
        player p=new player();
        p.setName(playerName);
        p.setPhone(phone);
        players.playerList.add(p);
    }
    @GetMapping("/playerSetting/printPlayers")
    public void printPlayers(){
        for(player p:players.playerList)
            System.out.println("name: "+ p.getName() +" phone: "+ p.getPhone());
    }
}

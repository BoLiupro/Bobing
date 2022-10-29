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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/player")
public class PlayerController {

    players players=new players();
    /*
    web page to set player number
     */
    @GetMapping("/Setting")
    public players playersSet(@RequestParam("playerNum") int playerNum) {
        players.playerList=new ArrayList<>(playerNum);
        return players;
    }

    /*
    web page to set every player information
     */
    @GetMapping("/Setting/player={playerName}")
    public void playerSet(@PathVariable("playerName") String playerName, @RequestParam("phone") String phone){
        player p=new player();
        p.setName(playerName);
        p.setPhone(phone);
        players.playerList.add(p);
    }
    @GetMapping("/printPlayers")
    public void printPlayers(){
        for(player p:players.playerList)
            System.out.println("name: "+ p.getName() +" phone: "+ p.getPhone());
    }
}

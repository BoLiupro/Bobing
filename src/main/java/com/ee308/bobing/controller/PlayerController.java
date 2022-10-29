package com.ee308.bobing.controller;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ee308.bobing.entity.player;
import com.ee308.bobing.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerMapper playerMapper;
    /*
    web page to set every player information
     */
    @GetMapping("/setting")
    public void setPlayers(@RequestParam("player_name") String player_name, @RequestParam("phone") String phone){
        player p=new player();
        p.setPlayer_name(player_name);
        p.setPhone(phone);
        playerMapper.insert(player_name,phone);
    }
    @GetMapping("/print")
    public List<player> printPlayers(){
        QueryWrapper<player> wrapper = new QueryWrapper();
        wrapper.gt("player_id", 0);
        return playerMapper.selectList(null);
    }
}

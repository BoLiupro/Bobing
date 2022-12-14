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
import com.ee308.bobing.entity.player;
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerMapper playerMapper;

    /*
    web page to set every player information
     */
    @ApiOperation("set the player information like name,phone,photo")
    @GetMapping("/setting")
    public ResponseResult setPlayers(@RequestParam("player_name") String player_name, @RequestParam("phone") String phone){
        player p=new player();
        p.setPlayer_name(player_name);
        p.setPhone(phone);
        playerMapper.insert(player_name,phone);
        return ResponseResult.ok().message("successfully set");
    }

    @ApiOperation("give all the infomation of all players")
    @GetMapping("/print")
    public ResponseResult printPlayers(){
        QueryWrapper<player> wrapper = new QueryWrapper();
        wrapper.gt("player_id", 0);
        return ResponseResult.ok().data("players",playerMapper.selectMaps(wrapper));
    }

    @ApiOperation("return the number of all players")
    @GetMapping("/Num")
    public ResponseResult playerNum(){
        int num=playerMapper.selectList((Wrapper<player>) new QueryWrapper().gt("player_id",0)).size();
        return ResponseResult.ok().data("player_num",num);
    }
}

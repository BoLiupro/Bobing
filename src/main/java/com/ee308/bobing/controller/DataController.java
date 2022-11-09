package com.ee308.bobing.controller;

import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.response.ResponseResult;
import com.ee308.bobing.service.impl.PlayerServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation("reset player and result dataset to none")
public class DataController {
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    ResultMapper resultMapper;

    @ApiOperation("reset the players and results when play a new game")
    @GetMapping("/reset")
    public ResponseResult deleteAllData(){
//        playerMapper.delete(null);
//        prizeMapper.delete(null);
//        resultMapper.delete(null);
        playerMapper.truncateTable();
//        prizeMapper.truncateTable();
        resultMapper.truncateTable();
        PlayerServiceImpl playerService =new PlayerServiceImpl();
        playerService.setCount(0);playerService.setRound(0);
        prizeMapper.resetPrizeNum(1,1);
        prizeMapper.resetPrizeNum(2,1);
        prizeMapper.resetPrizeNum(3,1);
        prizeMapper.resetPrizeNum(4,1);
        prizeMapper.resetPrizeNum(5,1);
        prizeMapper.resetPrizeNum(6,1);
        prizeMapper.resetPrizeNum(7,2);
        prizeMapper.resetPrizeNum(8,4);
        prizeMapper.resetPrizeNum(9,8);
        prizeMapper.resetPrizeNum(10,16);
        prizeMapper.resetPrizeNum(11,32);
        //total 11
        return ResponseResult.ok().message("successfully reset");
    }
}

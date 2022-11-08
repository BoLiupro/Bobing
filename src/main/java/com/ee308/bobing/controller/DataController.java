package com.ee308.bobing.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.response.ResponseResult;
import com.ee308.bobing.service.impl.Round;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation("")
public class DataController {
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    ResultMapper resultMapper;

    @GetMapping("/delete")
    public ResponseResult deleteAllData(){
//        playerMapper.delete(null);
//        prizeMapper.delete(null);
//        resultMapper.delete(null);
        playerMapper.truncateTable();
        prizeMapper.truncateTable();
        resultMapper.truncateTable();
        Round round=new Round();
        round.setCount(0);round.setRound(0);
        return ResponseResult.ok().message("successfully delete");
    }

}

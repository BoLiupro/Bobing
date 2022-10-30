package com.ee308.bobing.controller;

import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    ResultMapper resultMapper;

    @RequestMapping("/delete")
    public ResponseResult deleteAllData(){
//        playerMapper.delete(null);
//        prizeMapper.delete(null);
//        resultMapper.delete(null);
        playerMapper.truncateTable();
        prizeMapper.truncateTable();
        resultMapper.truncateTable();
        return ResponseResult.ok().message("successfully delete");
    }

}

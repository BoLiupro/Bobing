package com.ee308.bobing.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ee308.bobing.entity.prize;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */
@RestController
@RequestMapping("/prize")
public class PrizeController {

    @Autowired
    PrizeMapper prizeMapper;

    @GetMapping("/setting")
    public ResponseResult setting(@RequestParam("prize_level") String prize_level,
                                  @RequestParam("quantity") int quantity,
                                  @RequestParam("prize") String prize){
        prizeMapper.insert(prize_level,quantity,prize);
        return ResponseResult.ok().message("successfully add");
    }

    /*
    enquire prizes by prize_level .ie ex,zy_zy......
     */
    @GetMapping("/search")
    public ResponseResult search(@RequestParam("prize_level") String prize_level){
        QueryWrapper<prize> wrapper = new QueryWrapper();
        wrapper.eq("prize_level", prize_level);
        return ResponseResult.ok().data("prizes of "+prize_level, prizeMapper.selectMaps(wrapper));
    }


    @GetMapping("/print")
    public ResponseResult print(){
        QueryWrapper<prize> wrapper = new QueryWrapper();
        wrapper.gt("prize_id", 0);
        return ResponseResult.ok().data("prize",prizeMapper.selectMaps(wrapper));
    }

    @GetMapping("/minus")
    public ResponseResult minus(@RequestParam("prize_level") String prize_level){
        QueryWrapper<prize> wrapper = new QueryWrapper();
        wrapper.eq("prize_level", prize_level);
        prize p =prizeMapper.selectOne(wrapper);
        p.setQuantity(p.getQuantity()-1);
        prizeMapper.update(p,wrapper);
        return ResponseResult.ok().message("successfully update quantity").
                data("prize", prizeMapper.selectMaps(wrapper).get(0));
    }

}


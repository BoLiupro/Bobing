package com.ee308.bobing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ee308.bobing.entity.player;
import com.ee308.bobing.entity.prize;
import com.ee308.bobing.entity.result;
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.response.ResponseResult;
import com.ee308.bobing.service.dao.PlayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liubo
 * @since 2022-10-29
 */
@Service
@Component
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, player> implements PlayerService {

    static int count=0;
    static int round=0;
    static int roundNum=20;

    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    ResultMapper resultMapper;
    @Resource
    PrizeMapper prizeMapper;

    public static PlayerServiceImpl playerService;

    @PostConstruct//使用这个java注解，让静态实例联系到mapper接口，下边这个方法完全写上，修改为自己的东西
    public void init() {
        playerService = this;
        playerService.playerMapper = this.playerMapper;
        playerService.prizeMapper = this.prizeMapper;
        playerService.resultMapper = this.resultMapper;
    }

    PrizeServiceImpl prizeService=new PrizeServiceImpl();

    public int getPlayerNum() {
        int num=playerService.playerMapper.selectList((Wrapper<player>) new QueryWrapper().gt("player_id",0)).size();
        return num;
    }

    public void minus(String prize_level){
        if(prize_level.equals("none"))
            return;
        QueryWrapper<prize> wrapper = new QueryWrapper();
        wrapper.eq("prize_level", prize_level);
        prize p =playerService.prizeMapper.selectOne(wrapper);
        p.setQuantity(p.getQuantity()-1);
        playerService.prizeMapper.update(p,wrapper);
    }

    public Map getDiceResult(int player_id) {
        Dice dice = new Dice();
        String dice_result;
        int n = 0;
        for (int i = 0; i < 6; i++) {
            n += Math.pow(10, i) * dice.throwDice();
            /*
            1~6 dice will from a 6 digit number
            representing the 6 times dices result
             */
        }
        dice_result = String.valueOf(n);
        String prize_result = Awards.awardsMatch1(dice_result);
        Map map=new HashMap();
        map.put("dice_result",dice_result);
        map.put("prize_result",prize_result);
        return map;
    }

    public ResponseResult getResult(int player_id){
        Map resultMap=getDiceResult(player_id);
        String dice_result=resultMap.get("dice_result").toString();
        String prize_result=resultMap.get("prize_result").toString();
        /*
        if no prize left
         */
        result r=new result();
        String message = "null";
        r.setPlayer_id(player_id);r.setDice_result(dice_result);
        if(prize_result.equals("none")|| playerService.prizeService.isEmpty(prize_result)){
            r.setPrize_result("none");
            message="prize is "+prize_result+" but quantity is 0";
        }else{
            playerService.resultMapper.insert(player_id,dice_result,prize_result);r.setPrize_result(prize_result);
            minus(prize_result);
            r.setPrize_result(prize_result);
        }
        return ResponseResult.ok().data("round",getRound()).data("ifEnd",playerService.checkRound()).
        data("player",playerService.playerMapper.selectMaps((Wrapper<player>) new QueryWrapper().eq("player_id",player_id))).
        data("result",r).
        data("prize_quantity",new PrizeServiceImpl().getPrizeNum()).message(message);
    }

    public  void setCount(int count) {
        this.count = count;
    }

    public  void setRound(int round) {
        this.round = round;
    }

    public  int getRound() {
        int num=playerService.playerMapper.selectList((Wrapper<player>) new QueryWrapper().gt("player_id",0)).size();
        count++;
        if(count>=num) {
            round++;
            count = 0;
        }
        return round;
    }

    public void setRoundNum(int roundNum){
        this.roundNum=roundNum;
    }

    public boolean checkRound(){
        if(round>roundNum) return true;
        else return false;
    }
}

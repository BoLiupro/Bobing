package com.ee308.bobing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ee308.bobing.entity.player;
import com.ee308.bobing.entity.result;
import com.ee308.bobing.mapper.PlayerMapper;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.mapper.ResultMapper;
import com.ee308.bobing.service.dao.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
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
public class ResultServiceImpl extends ServiceImpl<ResultMapper, result> implements ResultService {
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    ResultMapper resultMapper;
    public static ResultServiceImpl resultService;

    @PostConstruct//使用这个java注解，让静态实例联系到mapper接口，下边这个方法完全写上，修改为自己的东西
    public void init() {
        resultService = this;
        resultService.prizeMapper = this.prizeMapper;
        resultService.playerMapper = this.playerMapper;
        resultService.resultMapper = this.resultMapper;
    }

    public List<Map<String,Object>> getAllResult(){
        int playerNum=resultService.playerMapper.selectList((Wrapper<player>) new QueryWrapper().gt("player_id",0)).size();
        List<Map<String,Object>> finalResultMap= new ArrayList<>();
        for(int i=1;i<=playerNum;i++){
            Wrapper<player> wrapper1 = (Wrapper<player>) new QueryWrapper().eq("player_id",i);
            Wrapper<result> wrapper2 = (Wrapper<result>) new QueryWrapper().eq("player_id",i);
            Map playerInfo= resultService.playerMapper.selectMaps(wrapper1).get(0);
            List<Map<String,Object>> maps =resultService.resultService.resultMapper.selectMaps(wrapper2);
            Map<String,Object> map1= new HashMap<>();
            map1.put("prize",record(maps));
            map1.put("player",playerInfo);
            finalResultMap.add(map1);
        }
        return finalResultMap;
    }

    public Map<String,Object> record(List<Map<String,Object>> maps){
        int [] resultArr=new int [11];
        ListIterator listIterator= maps.listIterator();
        while(listIterator.hasNext()){
            Map map = (Map) listIterator.next();
            String s= (String) map.get("prize_result");
            if(s.equals("zy_zycjh")) resultArr[0]++;
            if(s.equals("zy_lpRed")) resultArr[1]++;
            if(s.equals("zy_lpBlack")) resultArr[2]++;
            if(s.equals("zy_ww")) resultArr[3]++;
            if(s.equals("zy_wzdk")) resultArr[4]++;
            if(s.equals("zy_zy")) resultArr[5]++;
            if(s.equals("by")) resultArr[6]++;
            if(s.equals("th")) resultArr[7]++;
            if(s.equals("js")) resultArr[8]++;
            if(s.equals("jr")) resultArr[9]++;
            if(s.equals("xc")) resultArr[10]++;
        }
        Map resultMap=new HashMap();
        resultMap.put("zy_zycjh",resultArr[0]);
        resultMap.put("zy_lpRed",resultArr[1]);
        resultMap.put("zy_lpBlack",resultArr[2]);
        resultMap.put("zy_ww",resultArr[3]);
        resultMap.put("zy_wzdk",resultArr[4]);
        resultMap.put("zy_zy",resultArr[5]);
        resultMap.put("by",resultArr[6]);
        resultMap.put("th",resultArr[7]);
        resultMap.put("js",resultArr[8]);
        resultMap.put("jr",resultArr[9]);
        resultMap.put("xc",resultArr[10]);
        return resultMap;
    }
}

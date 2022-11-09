package com.ee308.bobing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ee308.bobing.entity.prize;
import com.ee308.bobing.mapper.PrizeMapper;
import com.ee308.bobing.service.dao.PrizeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, prize> implements PrizeService {

    @Autowired
    PrizeMapper prizeMapper;
    public static PrizeServiceImpl prizeService;

    @PostConstruct//使用这个java注解，让静态实例联系到mapper接口，下边这个方法完全写上，修改为自己的东西
    public void init() {
        prizeService = this;
        prizeService.prizeMapper = this.prizeMapper;
    }

    public boolean isEmpty(String prize_result){
        if(prizeService.prizeMapper.selectList((Wrapper<prize>) new QueryWrapper().
                eq("prize_level",prize_result)).get(0).getQuantity()==0)
            return true;
        else return false;
    }

    public List getPrizeNum(){
        ListIterator listIterator=prizeService.prizeMapper.selectList((Wrapper<prize>) new QueryWrapper().gt("prize_id",0)).listIterator();
        List prize_Num = new ArrayList<>();
        while(listIterator.hasNext()){
            prize prize= (com.ee308.bobing.entity.prize) listIterator.next();
            prize_Num.add(prize.getQuantity());
        }
        return prize_Num;
    }
}

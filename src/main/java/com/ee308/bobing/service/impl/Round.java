package com.ee308.bobing.service.impl;


public class Round {
    static int count=0;
    static int round=0;

    public  void setCount(int count) {
        Round.count = count;
    }

    public  void setRound(int round) {
        Round.round = round;
    }

    public  int getRound(int num) {
        count++;
        if(count>=num) {
            round++;
            count = 0;
        }
        return round;
    }
}

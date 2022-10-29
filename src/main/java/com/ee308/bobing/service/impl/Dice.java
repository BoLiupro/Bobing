package com.ee308.bobing.service.impl;

import org.springframework.stereotype.Service;

import java.util.Random;

public class Dice {
    public int throwDice(){
        Random r=new Random();
        return r.nextInt(6)+1;
    }
}

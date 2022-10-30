package com.ee308.bobing.service.impl;

import java.util.Random;

public class Dice {
    public int throwDice(){
        Random r=new Random();
        return r.nextInt(6)+1;
    }
}

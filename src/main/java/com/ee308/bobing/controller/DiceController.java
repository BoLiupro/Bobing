package com.ee308.bobing.controller;

import java.util.Random;

public class DiceController {
    public int throwDice(){
        Random r=new Random();
        return r.nextInt(6)+1;
    }
}

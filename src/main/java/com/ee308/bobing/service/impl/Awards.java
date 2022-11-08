package com.ee308.bobing.service.impl;

public class Awards {
    public static String awardsMatch1(String dice_result){
        int []ar=new int[6];
        for(int x=0;x<6;x++){
            char c=dice_result.charAt(x);
            for(int y=0;y<6;y++){
                if((c-'0')==y+1) ar[y]++;
            }
        }
        return awardsMatch2(ar);
    }


    public static String awardsMatch2(int[] ar){
        int four_num = ar[3];//the 4th element represent the num of 4
        switch(four_num){
            case 1: return "xc";//秀才
            case 2: return "jr";//举人
            case 3: return "th";//探花
            case 5: return "zy_ww";//状元_五王
            case 6: return "zy_lpRed";//状元_六抔红
        }

        if(ar[0]*ar[1]*ar[2]*ar[3]*ar[4]*ar[5]==1)
            return "by";//榜眼

        if(ar[0]==4|ar[1]==4|ar[2]==4|ar[3]==4|ar[4]==4|ar[5]==4)
            return "js";//进士

        if(ar[5]==5)
            return "zy_wzdk";//状元_五子登科

        if(ar[5]==6)
            return "zy_lpBlack";//状元_六抔黑

        if(ar[3]==4){
            if(ar[0]==2)
                return "zy_zycjh";//状元_状元插金花
            else
                return "zy_zy";//状元_状元
        }

        return "none";
    }

}

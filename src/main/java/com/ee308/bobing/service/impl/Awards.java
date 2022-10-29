package com.ee308.bobing.service.impl;

public class Awards {

    String awardsMatch1(String dice_result){
            int []ar=new int[6];
            for(int x=0;x<6;x++){
                char c=dice_result.charAt(x);
                for(int y=1;y<7;y++){
                    if(c==y) ar[y-1]++;
                }
            }

    }


    String awardsMatch2(int[] ar){
        int four_num = ar[3];//the 4th element represent the num of 4
        switch(four_num){
            case 1: return "xc";break;//秀才
            case 2: return "jr";break;//举人
            case 3: return "th";break;//探花
        }

        if(ar[0]*ar[1]*ar[2]*ar[3]*ar[4]*ar[5]==1)
            return "by";//榜眼

        if(ar[1]==4)
            return "js";//进士

        boolean A=ar[3]>=4;
        boolean B=ar[5]>=5;
        boolean C=ar[0]==2&ar[3]==4;
        if(A|B|C)
            return "zy";//状元
    }
}

package com.example.mankala1;

import android.widget.Toast;

public class GameModule {

    public GameModule() {
    }

    public void Turn1(Pit[]arr1, Pit[]arr2, int i)
    {
            int hand=arr1[i].getStoneCount();
            arr1[i].setStoneCount(0);
            for (int j = i +1; j <=5; j++) {
                if(hand>0)
                    arr1[j].setStoneCount(arr1[j].getStoneCount() + 1);
                hand--;
            }
            if(hand>0)
                arr1[6].setStoneCount(arr1[6].getStoneCount() + 1);
            hand--;
            for (int j = 0; j < hand; j++) {
                arr2[j].setStoneCount(arr2[j].getStoneCount() +1);
            }
    }
    public void Turn2(Pit[] arr1, Pit[] arr2, int i)
    {
        int hand=arr1[i].getStoneCount();
        arr1[i].setStoneCount(0);
        for (int j = i +1; j <=5; j++) {
            if(hand>0)
                arr1[j].setStoneCount(arr1[j].getStoneCount() + 1);
            hand--;
        }
        if(hand>0)
            arr1[6].setStoneCount(arr1[6].getStoneCount() + 1);
        hand--;
        for (int j = 0; j < hand; j++) {
            arr2[j].setStoneCount(arr2[j].getStoneCount() +1);
        }
        if(this.IsWon(arr1,arr2))
        {}

    }
    public boolean IsWon(Pit[]arr1,Pit[]arr2)
    {
        boolean arr1won=true,arr2won=true;
        for (int i = 0; i < arr1.length-1; i++)
        {
            if (arr1[i].getStoneCount()!=0)
            {
                arr1won=false;
            }
            if (arr2[i].getStoneCount()!=0)
            {
                arr2won=false;
            }
        }
        if(arr1won==true||arr2won==true)
        {
            return true;
        }
        return false;
    }

}

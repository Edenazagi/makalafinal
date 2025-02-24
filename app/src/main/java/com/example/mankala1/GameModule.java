package com.example.mankala1;

import android.widget.Toast;

public class GameModule {

    public GameModule() {
    }

    public void Turn1(Pit[]arr1, Pit[]arr2, int i)
    {
            int hand=arr1[i].getStoneCount();
            int last=arr1[i].getStoneCount()+i;
            if(last<6&&arr2[Mool(last)].getStoneCount()>0&&arr1[last].getStoneCount()==0)
            {
                int a=arr2[Mool(last)].getStoneCount();
                arr2[Mool(last)].setStoneCount(0);
                arr1[last].setStoneCount(0);
                arr1[6].setStoneCount(a + 1);
                hand--;
            }
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
        int last=arr2[i].getStoneCount()+i;
        if(last<6&&arr1[Mool(last)].getStoneCount()>0&&arr2[last].getStoneCount()==0)
        {
            int a=arr1[Mool(last)].getStoneCount();
            arr1[Mool(last)].setStoneCount(0);
            arr2[last].setStoneCount(0);
            arr2[6].setStoneCount(a + 1);
            hand--;
        }
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
            arr1[j].setStoneCount(arr1[j].getStoneCount() +1);
        }

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
    private static int Mool(int a)
    {
        if (a==0)return 5;
        if (a==1)return 4;
        if (a==2)return 3;
        if (a==3)return 2;
        if (a==4)return 1;
        if (a==5)return 0;
        return -1;

    }

}

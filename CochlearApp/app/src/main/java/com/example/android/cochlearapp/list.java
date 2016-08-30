//This class is responsible for generating the order of materials to be presented in the experiment


package com.example.android.cochlearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class list extends AppCompatActivity {

    public static int i = 0;

    public static String get(){


        //The list gets created and shuffled everytime it's called (probably not the best method)
        //The user ID is used as the random seed, so the list is the same each time it's called for that person, yet different between subjects, so it works out
        ArrayList<String> list = new ArrayList<String>();
        list.add("BAIT");
        list.add("BART");
        list.add("BAT");
        list.add("BEET");
        list.add("BET");
        list.add("BIT");
        list.add("BITE");
        list.add("BOOT");
        list.add("BOUGHT");
        list.add("BOUT");
        list.add("BURT");
        list.add("BUT");
        Collections.shuffle(list, new Random(Variables.getUserID()));
        i++;
        Variables.setTrial(i);
        return list.get(i-1);

    }

    public static void release(){
        System.exit(0);
    }


}

package com.billy.bloodbank;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class formRequestData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_data);

        Bundle bundle = getIntent().getExtras();
        final int[][] array1 = (int[][])bundle.getSerializable("24Array");
        final int[][] array2 = (int[][])bundle.getSerializable("48Array");

        final HashMap<String,Integer> HashMap1 = new HashMap<String, Integer>();
        HashMap1.put("A+",0);
        HashMap1.put("A-",1);
        HashMap1.put("B+",2);
        HashMap1.put("B-",3);
        HashMap1.put("O+",4);
        HashMap1.put("O-",5);
        HashMap1.put("AB+",6);
        HashMap1.put("AB-",7);


        final HashMap<String,Integer> HashMap2 = new HashMap<String, Integer>();
        HashMap2.put("Whole Blood",0);
        HashMap2.put("Packed Cells",1);
        HashMap2.put("FFP",2);
        HashMap2.put("Plasma",3);
        HashMap2.put("Platelet conc.",4);

        final String Bg[] = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};
        final String Bt[] = {"Whole Blood","Packed Cells","FFP","Plasma","Platelet conc."};

        for(int i=0; i<8; i++)
        {
            for(int j=0; j<5; j++)
            {
                Log.d("Value",Bg[i] + " " + Bt[j]);
                Log.d("Value",Integer.toString(array1[i][j]));
                Log.d("Value",Bg[i] + " " + Bt[j]);
                Log.d("Value",Integer.toString(array2[i][j]));
            }
        }

    }
}

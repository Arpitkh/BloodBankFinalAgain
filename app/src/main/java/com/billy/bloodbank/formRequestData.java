/*Java File for the Form Request Cuunter Display */
package com.billy.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class formRequestData extends AppCompatActivity {

    DatabaseReference databasePatient3, databasePatient4;
    private ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_count);

        final RecyclerView bloodType_list = findViewById(R.id.bloodType_list);
        bloodType_list.setLayoutManager(new LinearLayoutManager(this));

        final int[][] array1 = makeArray1();
        final int[][] array2 = makeArray2();
        final int[][] Array = new int[8][5];

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

        progDailog = ProgressDialog.show(formRequestData.this, "Loading","Fetching Request Information...", true);
        progDailog.setCancelable(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progDailog.dismiss();
                for(int i=0; i<8; i++)
                {
                    for(int j=0; j<5; j++)
                    {
                        Array[i][j] = array1[i][j]+array2[i][j];
                    }
                }

                BloodType[] bloodType = new BloodType[40] ;

                for(int k=0; k<bloodType.length; k++)
                {
                    bloodType[k] = new BloodType();
                }

                for(int k=0; k<8; k++)
                {
                    for(int l=0; l<5; l++)
                    {
                        bloodType[(k*5)+l].setBloodGroup(Bg[k]);
                        bloodType[(k*5)+l].setComponentName(Bt[l]);
                        bloodType[(k*5)+l].setUnitsAvailable(Integer.toString(Array[k][l]));
                    }
                }

                bloodType_list.setAdapter(new bloodCountAdapter(formRequestData.this,bloodType));
            }
        }, 5000);
    }

    public int[][] makeArray1(){
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

        final int[][] Array1 = new int[8][5];
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<5; j++)
            {
                Array1[i][j] = 0;
            }
        }

        databasePatient3 = FirebaseDatabase.getInstance().getReference("Patient/24 Hrs");
        databasePatient3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    String grp = itemSnapshot.child("requiredBloodGroup").getValue().toString();
                    String type = itemSnapshot.child("requiredBloodType").getValue().toString();
                    long units = itemSnapshot.child("unitRequired").getValue(long.class);

                    Array1[HashMap1.get(grp)][HashMap2.get(type)]+= units;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return Array1;
    }  //makes an Array for the 24 Hours blood request count

    public int[][] makeArray2(){
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

        final int[][] Array2 = new int[8][5];
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<5; j++)
            {
                Array2[i][j] = 0;
            }
        }

        databasePatient4 = FirebaseDatabase.getInstance().getReference("Patient/48 Hrs");
        databasePatient4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    String grp = itemSnapshot.child("requiredBloodGroup").getValue().toString();
                    String type = itemSnapshot.child("requiredBloodType").getValue().toString();
                    long units = itemSnapshot.child("unitRequired").getValue(long.class);

                    Array2[HashMap1.get(grp)][HashMap2.get(type)]+= units;

                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return Array2;
    }   //makes an array for the 48 Hours blood request count

    @Override
    public void onBackPressed() {
        Intent intent25 = new Intent(formRequestData.this, actTwo.class);
        startActivity(intent25);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}

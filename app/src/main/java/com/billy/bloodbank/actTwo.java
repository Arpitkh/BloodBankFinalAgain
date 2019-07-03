package com.billy.bloodbank;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class actTwo extends AppCompatActivity {

    Button getformButton,previous;
    DatabaseReference databasePatient1, databasePatient2, databasePatient3, databasePatient4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_two);

        getformButton =findViewById(R.id.getformButton);
        getformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder instBuilder = new AlertDialog.Builder(actTwo.this);
                instBuilder.setTitle("IMPORTANT INSTRUCTIONS");
                View instview = getLayoutInflater().inflate(R.layout.instructions,null);

                Button accept =instview.findViewById(R.id.accept) ;
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(actTwo.this, Application_Form.class);
                        startActivity(intent);
                    }
                });
                instBuilder.setView(instview);
                AlertDialog dialog = instBuilder.create();
                dialog.show();
            }

        });

        previous = findViewById(R.id.prevAgain);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(actTwo.this, OptionActivity.class);
                startActivity(intent11);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    public void requestData(View view){


        long cutoff = new Date().getTime() - 240000;
        long cutoff2 = new Date().getTime() - 300000;
        /*databasePatient1 = FirebaseDatabase.getInstance().getReference("Patient/24 Hrs");
        Query oldItems = databasePatient1.orderByChild("timeStamp").endAt(cutoff);
        oldItems.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    itemSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });



        databasePatient2 = FirebaseDatabase.getInstance().getReference("Patient/48 Hrs");
        Query oldItems2 = databasePatient2.orderByChild("timeStamp").endAt(cutoff2);
        oldItems2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    itemSnapshot.getRef().removeValue();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        })*/

        final Intent intent = new Intent(actTwo.this, formRequestData.class);
        final Bundle bundle = new Bundle();

        final int[][] array1 = makeArray1();
        final int[][] array2 = makeArray2();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                bundle.putSerializable("24Array", array1);
                bundle.putSerializable("48Array", array2);
                intent.putExtras(bundle);
                startActivity(intent);
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
    }

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
    }
}

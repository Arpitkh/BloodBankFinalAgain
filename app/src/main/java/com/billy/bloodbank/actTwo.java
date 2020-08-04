/*This is the Java file for the options screen created after clicking on the Form Request Button"*/
package com.billy.bloodbank;

import android.content.DialogInterface;// For creating Dialog Boxes
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;


public class actTwo extends AppCompatActivity {

    Button getformButton,previous;
    DatabaseReference databasePatient1, databasePatient2;
    AlertDialog.Builder builder;//For Creating Dialog Boxes

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

    public void requestData(View view){         //Onclick Listener pressing which deletes the entries in the database that are 24 Hrs/48 Hrs old
        dataRemoval();
    }

    public void dataRemoval(){
        long cutoff = new Date().getTime() - 240000;
        long cutoff2 = new Date().getTime() - 480000;
        databasePatient1 = FirebaseDatabase.getInstance().getReference("Patient/24 Hrs");
        Query oldItems = databasePatient1.orderByChild("timeStamp").endAt(cutoff);
        oldItems.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    itemSnapshot.getRef().removeValue();
                }
                final Intent intent = new Intent(actTwo.this, formRequestData.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

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

               /*builder = new AlertDialog.Builder(actTwo.this);

                builder.setMessage("Please note that this only shows request submitted by app users")
                        .setCancelable(false)
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {*/
                                final Intent intent = new Intent(actTwo.this, formRequestData.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                           /* }
                        })
                        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });

                AlertDialog alert = builder.create();

                alert.setTitle("Important Information");
                alert.show();*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(actTwo.this, OptionActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}



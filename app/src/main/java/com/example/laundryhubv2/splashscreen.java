package com.example.laundryhubv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class splashscreen extends AppCompatActivity {


    ImageView imageView;
    TextView textView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth Fauthh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView7);

        imageView.animate().alpha(0f).setDuration(0);
        textView.animate().alpha(0f).setDuration(0);

        imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                textView.animate().alpha(1f).setDuration(800);
            }

        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Fauthh = FirebaseAuth.getInstance();
                if(Fauthh.getCurrentUser()!=null){
                    if(Fauthh.getCurrentUser().isEmailVerified()){
                        Fauthh=FirebaseAuth.getInstance();

                        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid()+"/Role");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot){
                                String role = snapshot.getValue(String.class);
                                if(role.equals("Customer")){
                                    startActivity(new Intent(splashscreen.this,SelectType.class));
                                    finish();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(splashscreen.this,error.getMessage(),Toast.LENGTH_LONG).show();

                            }
                        });
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(splashscreen.this);
                        builder.setMessage("Check whether you have verified your email, otherwise please verify to log in");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(splashscreen.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        Fauthh.signOut();
                    }
                }else{
                    Intent intent = new Intent(splashscreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);
    }

}
package com.oguzhann.kennyiyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView Timetext;
    TextView Scoretext;
    ImageView k1;
    ImageView k2;
    ImageView k3;
    ImageView k4;
    ImageView k5;
    ImageView k6;
    ImageView k7;
    ImageView k8;
    ImageView k9;
    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;


    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences =getSharedPreferences("com.oguzhann.kennyiyakala", Context.MODE_PRIVATE);


        Timetext =(TextView) findViewById(R.id.Timetext);
        Scoretext= (TextView) findViewById(R.id.Scoretext);


        k1=findViewById(R.id.kenny1);
        k2=findViewById(R.id.kenny2);
        k3=findViewById(R.id.kenny3);
        k4=findViewById(R.id.kenny4);
        k5=findViewById(R.id.kenny5);
        k6=findViewById(R.id.kenny6);
        k7=findViewById(R.id.kenny7);
        k8=findViewById(R.id.kenny8);
        k9=findViewById(R.id.kenny9);

        imageArray = new ImageView[] {k1,k2,k3,k4,k5,k6,k7,k8,k9};
        saklakenny();

        score=0;
        new CountDownTimer(15000,1000){

            @Override // her bir saniye bitince ne yapayım
            public void onTick(long millisUntilFinished){
                Timetext.setText("Time:"+millisUntilFinished/1000);

            }
            @Override// tamamen saniyeler bitince ne yapayım
            public void onFinish(){

                Timetext.setText("Time off");
                handler.removeCallbacks(runnable);
                for(ImageView imageView: imageArray){
                    imageView.setVisibility(View.INVISIBLE);

                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Tekrar??");
                alert.setMessage("yeniden başlamak ister misin çaylak?");
                alert.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //yeniden çagır sayfayı
                        Intent intent =getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Oyun Bitti", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }

        }.start();

    }

    public void skoruarttir(View view){
        score++;
        Scoretext.setText("score : "+score);

    }

    public  void  saklakenny(){

        handler =new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {

                for(ImageView imageView: imageArray){
                    imageView.setVisibility(View.INVISIBLE);

                }
                Random random =new Random();
                int i =random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,270);

            }
        };

        handler.post(runnable);



    }


}
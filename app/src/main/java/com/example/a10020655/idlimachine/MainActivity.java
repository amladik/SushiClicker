package com.example.a10020655.idlimachine;

import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        static int sCookie;
        static TextView sumoText;
        static TextView coinText;
        static int gramEcon;
        static boolean fader;
        static int coinEcon;
        static int grandma = 0;
        static int upgradeMover = -400;
        static ImageView sushiPic;
        static ImageView storeCoin;
        static int coinerPrice;
        static ImageView storeSumo;
        static ImageView pointPic;
        static Animation scaleAnimation;
        static Animation scaleAnimation2;
        static TextView points;
        static Animation fadeinAnimation;
        static Animation fadeoutAnimation;
        static TextView ppc;
        static ArrayList<ImageView> pointList = new ArrayList<>();
    static ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fadeinAnimation = new AlphaAnimation(1.0f, 0.0f);
        fadeinAnimation.setDuration(2000);
        fadeoutAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeoutAnimation.setDuration(2000);
        coinerPrice = 1;

        sushiPic = findViewById(R.id.suhsi);
        layout =  findViewById(R.id.layout); layout.setBackgroundColor(Color.BLACK);
        storeSumo = findViewById(R.id.sumoStore);
        sumoText = findViewById(R.id.sumoCosttext);
        coinText = findViewById(R.id.sumoCosttext2);
        storeCoin = findViewById(R.id.coinStore);
        ppc = findViewById(R.id.ppc);
        pointPic=new ImageView(this);
        pointPic.setImageResource(R.drawable.pointimage);
        pointPic.setScaleX((float).24);
        pointPic.setScaleY((float).24);
        gramEcon = 10;
        coinEcon = 5;
        scaleAnimation = new ScaleAnimation(.75f, 1.0f, .75f, 1.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        scaleAnimation.setDuration(200);
        scaleAnimation2 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF,.5f);
        scaleAnimation2.setDuration(2000);
        points = findViewById(R.id.nigiricounter);
        sCookie = 0;

        new passiveIncome().start();

        new canBuy().start();

        new ClickThread().start();

        sumoText.setText("Cost: 10");
        coinText.setText("Cost: 5");



        storeCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sCookie >= coinEcon){
                    coinerPrice*=2;
                    sCookie-= coinEcon;
                    points.setText( sCookie+ "");
                    coinEcon*=5;
                    ppc.setText("Points Per Click: " + coinerPrice);
                    coinText.setText("Cost: " + coinEcon);

                }
            }
        });

        storeSumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sCookie>gramEcon) {
                    grandma += 5;
                    sCookie-=gramEcon;
                    if(sCookie> gramEcon) {
                        fader = true;
                    }
                    gramEcon*=2;
                    sumoText.setText("Cost: "+ gramEcon);
                    points.setText(sCookie+ "");
                    ImageView sumo = new ImageView(MainActivity.this);
                    sumo.setId(View.generateViewId());
                    sumo.setImageResource(R.drawable.sumo);
                    sumo.setScaleX((float).2);
                    sumo.setScaleY((float).2);
                    sumo.setX(upgradeMover);

                    sumo.setY((680) + (float)Math.random()*20-10);

                    layout.addView(sumo);
                    sumo.startAnimation(scaleAnimation2);

                    upgradeMover +=60;



                }
            }
        });


        }
    public  void click(){

            sushiPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    TranslateAnimation picMover = new TranslateAnimation(0, (float)Math.random()*200-100,0,-450);
                    picMover.setDuration(500);



                    final ImageView coin = new ImageView(MainActivity.this);
                    coin.setId(View.generateViewId());
                    coin.setImageResource(R.drawable.pointimage);
                    coin.setScaleX((float).10);
                    coin.setScaleY((float).10);
                    coin.setX((float)Math.random()*600);
                    coin.setY(370);


                    layout.addView(coin);
                    coin.startAnimation(picMover);
                    picMover.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            layout.removeView(coin);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    v.startAnimation(scaleAnimation);
                    sCookie+= coinerPrice;
                    if(sCookie> gramEcon) {
                        fader = true;
                    }


                    points.setText(String.valueOf(sCookie));

                }
            });

    }

    public synchronized  void addMethod(){

            sCookie+= (2*grandma);
        if(sCookie> gramEcon) {
            fader = true;
        }
            points.post(new Runnable() {
                @Override
                public void run() {
                    points.setText(sCookie+"" );
                }
            });

    }

    public synchronized void buyornah(){

    }







    public class ClickThread extends Thread{

        public void run(){

            try{
                Thread.sleep(2);
            }catch(Exception e){

                 }
            click();



        }
    }

    public class passiveIncome extends Thread {

        public void run() {
            while (true) {
                addMethod();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public class canBuy extends Thread {
        public void run(){
            while (true){
                buyornah();
            }

        }

    }








}

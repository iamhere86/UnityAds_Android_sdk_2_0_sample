package com.aylin.unityads.myadssdk20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;


import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public class MainActivity extends AppCompatActivity {

    private String _exampleAppLogTag = "UnityAdsExample";
    public int coins;
    final private UnityAdsListener unityAdsListener = new UnityAdsListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(_exampleAppLogTag, "UnityAdsTestStartActivity->onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coins = 0;
        UnityAds.setDebugMode(true);


    }

    public void buttonOnClick(View v){
        Button mButton = (Button)findViewById(R.id.mybutton);

                if(UnityAds.isReady("rewardedVideo")){
                    Log.d(_exampleAppLogTag, "isReady()->true");
                    UnityAds.show(this, "rewardedVideo");

                }else{
                    mButton.setText("Initializing");
                    UnityAds.initialize(this, "1159239", unityAdsListener, false);
                }
    }

    public void rewardPlayer(){

        Log.d(_exampleAppLogTag, "coins :" + coins);
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        coins= coins+10;
        textView1.setText("coins :" + Integer.toString(coins));
        Log.d(_exampleAppLogTag, "coins :" + coins);
    }

    private class UnityAdsListener implements IUnityAdsListener{

        @Override
        public void onUnityAdsReady(String s) {
            Log.d(_exampleAppLogTag, "onUnityAdsReady");
            final Button mButton = (Button)findViewById(R.id.mybutton);
            mButton.setText("Show ads");

        }

        @Override
        public void onUnityAdsStart(String s) {

        }

        @Override
        public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
            if(finishState != UnityAds.FinishState.SKIPPED){
                Log.d(_exampleAppLogTag, "Video watching completed!");
                rewardPlayer();
            }
            else
                Log.d(_exampleAppLogTag, "Video skipped");


        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {

        }
    }
}

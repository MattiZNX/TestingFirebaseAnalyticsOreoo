package com.example.testingfirebaseanalyticsoreo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FirebaseAnalytics mFirebaseAnalytics;
    Button button, crashbutton;
    Bundle b;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = new Bundle();
        button = findViewById(R.id.button);
        crashbutton = findViewById(R.id.crashbutton);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MobileAds.initialize(this, "ca-app-pub-8027314584296339~2479856084");
    }

    public void onClickB(View v){
        b.putInt(FirebaseAnalytics.Param.ITEM_ID, button.getId());
        b.putString(FirebaseAnalytics.Param.ITEM_NAME, button.getText().toString());
        b.putString("TimeonClickB" , Calendar.getInstance().getTime().toString());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, b);
        Toast t = Toast.makeText(this, "Toast di test ... " + button.getId() + button.getText().toString() , Toast.LENGTH_LONG);
        t.show();
    }

    public void onClickCrash(View v){
        Crashlytics.getInstance().crash(); //force crash
        Crashlytics.setString("Crash avvenuto da questo bottone : " , crashbutton.getText().toString());
    }
}

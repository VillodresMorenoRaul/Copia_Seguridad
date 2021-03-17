package com.example.receptoranunciosprueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity1 extends Activity {

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }


    // broadcast a custom intent.
    public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction("com.receptor.CUSTOM_INTENT");
        sendBroadcast(intent);
    }
}
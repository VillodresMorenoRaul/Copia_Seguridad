package com.example.receptoranunciosconnotificacion;

import android.app.AlarmManager ;
import android.app.Notification ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.content.Context ;
import android.content.Intent ;
import android.os.Bundle ;
import android.os.SystemClock ;
import android.view.Menu ;
import android.view.MenuItem ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super .onCreate(savedInstanceState) ;
        setContentView(R.layout. activity_main ) ;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu. main_menu , menu) ;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id. action_5 :
                scheduleNotification(getNotification( "5 second delay" ) , 5000 ) ;
                return true;
            case R.id. action_10 :
                scheduleNotification(getNotification( "10 second delay" ) , 10000 ) ;
                return true;
            case R.id. action_30 :
                scheduleNotification(getNotification( "30 second delay" ) , 30000 ) ;
                return true;
            default :
                return super .onOptionsItemSelected(item) ;
        }
    }
    private void scheduleNotification (Notification notification , int delay) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        long futureInMillis = SystemClock. elapsedRealtime () + delay ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
    }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "Scheduled Notification" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
}
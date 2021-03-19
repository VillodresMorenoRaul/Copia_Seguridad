package com.example.hlc_to04_raul_villodres;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadService extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    public static String default_notification_channel_id = "default" ;
    public static double VALORES;

    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //Nuestro onStartCommand se encargará de obtener la URL de nuestro mainActivity y realizar una acción del okhttp, además de mostrar una notificación
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        URL url = null;

        try {
            url = new URL(MainActivity1.URL);
            descargaOkHTTP(url);
            scheduleNotification(getNotification( "El ratio ha sido descargado" )) ;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mostrarMensaje("Error en la URL: " + MainActivity1.URL);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mostrarMensaje("Servicio destruido");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void descargaOkHTTP(URL web) {

        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(web).build();

        client.newCall(request).enqueue(new Callback() {

            //En caso de fallo muestra un error
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Error: ", e.getMessage());
            }

            //Nuestro OnResponse se encarga de asignar el valor obtenido de la página web que hemos especificado
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        //throw new IOException("Unexpected code " + response);
                        Log.e("Error: ", "Unexpected code " + response);
                    } else {
                        // Read data on the worker thread
                        final String responseData = response.body().string();
                        // guardar el fichero descargado en memoria externa
                            Log.i("Descarga: ", "fichero descargado");
                            MainActivity1.Ratio = Double.parseDouble(responseData);
                    }
                }
            }
        });
    }

    //Atajo para mostrar toasts
    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    //Este método realiza los ajustes pertinentes en la notificación
    private void scheduleNotification (Notification notification) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        long futureInMillis = SystemClock. elapsedRealtime ();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
    }

    //Y este otro la muestra.
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "Notificación personalizada" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
}
package com.example.firebase_fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification() != null){
            String sender = remoteMessage.getFrom();
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Log.d("main","sender = "+sender);
            Log.d("main","title = "+title);
            Log.d("main","body = "+body);

            sendNotification(title,body);
        }
    }

    private void sendNotification(String title, String body) {

        Intent intent = new Intent(this, TrainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);//手機裡面可以設定鈴聲
        Bitmap bmpInfo = BitmapFactory.decodeResource(getResources(), R.drawable.info4);

        String channelID = "my_channel";
        //手動建立的通知
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelID);
        notificationBuilder.setSmallIcon(R.drawable.info4);
        notificationBuilder.setLargeIcon(bmpInfo);
        notificationBuilder.setSound(soundURI);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(pendingIntent);


        NotificationManager notiftManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //TODO:判別版本有不同要求
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelID, "Channel name -Janet", NotificationManager.IMPORTANCE_DEFAULT);
            notiftManager.createNotificationChannel(channel);


        }
        Notification notification = notificationBuilder.build();
        notiftManager.notify(0,notification);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("main","new token"+s);
    }
}

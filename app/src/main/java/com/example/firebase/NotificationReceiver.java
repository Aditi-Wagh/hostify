package com.example.firebase;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @Override

    public void onReceive(Context context, Intent intent) {
        // Create and configure the notification
        Calendar currentCalendar = Calendar.getInstance();
        int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        if(currentHour==8) {
            builder.setSmallIcon(R.drawable.notification_bell)
                    .setContentTitle("Breakfast Time!!")
                    .setContentText("Breakfast is ready. It will be available from 8.00 a.m. to 10.a.m..")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }

        if(currentHour==12) {
            builder.setSmallIcon(R.drawable.notification_bell)
                    .setContentTitle("Lunch Time!!")
                    .setContentText("Lunch is ready. It will be available from 12.00 p.m. to 2.p.m..")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }

        if(currentHour==20) {
            builder.setSmallIcon(R.drawable.notification_bell)
                    .setContentTitle("Dinner Time!!")
                    .setContentText("Dinner is ready. It will be available from 8.00 p.m. to 10.p.m..")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }

        // Create an empty intent


        builder.setAutoCancel(true); // Automatically remove the notification when tapped

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Show the notification
        notificationManager.notify(0, builder.build());
    }
}

package com.sashakhyzhun.androidbarbershopmanagementprototype.utils

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.MainActivity

@TargetApi(Build.VERSION_CODES.N)
fun Context.notifyAboutNewRequest(text: String, extraKey: String) {
    val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notificationId = 1
    val channelId = "inTimeSensorChannelId"
    val channelName = "inTimeSensorChannelName"
    val importance = NotificationManager.IMPORTANCE_HIGH

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val mChannel = NotificationChannel(
                channelId, channelName, importance)
        notificationManager.createNotificationChannel(mChannel)
    }

    val mBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.mipmap.sym_def_app_icon)
            .setContentTitle("New incoming request!")
            .setDefaults(Notification.DEFAULT_SOUND)
            .setAutoCancel(true)
            .setContentText(text)

    val stackBuilder = TaskStackBuilder.create(this)

    val intent = Intent(this, MainActivity::class.java)
    intent.putExtra(extraKey, true)

    stackBuilder.addNextIntent(intent)
    val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
    )

    mBuilder.setContentIntent(resultPendingIntent)
    notificationManager.notify(notificationId, mBuilder.build())
}

package com.example.androidcomponents.utils

import android.R.attr
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidcomponents.R


class Notifications(private val context: Context) {

    fun createNotification(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            var name:CharSequence = "gfggbg"
            var desc:String ="thi is "
            var notificationChannel = NotificationChannel(1.toString(),name,NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.description =desc

            var notificationManager:NotificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun addNotification(foodName: String?, bitmap: Bitmap?) {
        var builder:NotificationCompat.Builder = NotificationCompat.Builder(context.applicationContext,"1")
        builder.setSmallIcon(R.drawable.rating_drawable)
        builder.setLargeIcon(bitmap)
        builder.setContentTitle(foodName)
        builder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
        builder.setAutoCancel(true)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT

        var notificationManagerCompat = NotificationManagerCompat.from(context.applicationContext)
        notificationManagerCompat.notify(1,builder.build())
        
    }


}
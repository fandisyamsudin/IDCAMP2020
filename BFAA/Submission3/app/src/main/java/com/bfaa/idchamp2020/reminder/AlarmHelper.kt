package com.bfaa.idchamp2020.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.reminder.AlarmReceiver.Companion.EXTRA_MESSAGE
import com.bfaa.idchamp2020.reminder.AlarmReceiver.Companion.EXTRA_TITLE
import es.dmoral.toasty.Toasty
import java.util.*

object AlarmHelper {
    fun alarmOn(context: Context, tittle: String, message: String, requestCode: Int, time: Calendar) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(EXTRA_TITLE, tittle)
            putExtra(EXTRA_MESSAGE, message)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            time.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toasty.success(context, (R.string.on_reminder), Toast.LENGTH_SHORT).show()
    }
    fun alarmOff(context: Context, requestCode: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0).also {
            it.cancel()
        }
        alarmManager.cancel(pendingIntent)
        Toasty.info(context, (R.string.off_reminder), Toast.LENGTH_SHORT).show()
    }
}
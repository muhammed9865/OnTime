package com.muhammed.ontime.service

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.muhammed.ontime.Constants
import com.muhammed.ontime.R

class NotifyScheduleWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        val title = inputData.getString(Constants.SCHEDULE_TITLE) ?: "On.time"
        val place = inputData.getString(Constants.SCHEDULE_PLACE) ?: "None"
        val note = inputData.getString(Constants.SCHEDULE_NOTE) ?: "None"

        val notification = createScheduleNotification(title, place, note)
        val nm: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(1, notification.build())

        return Result.success()
    }

    private fun createScheduleNotification(
        scheduleTitle: String,
        place: String,
        note: String
    ): NotificationCompat.Builder =
        NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setContentTitle("$scheduleTitle is Now!")
            .setContentText("Head to $place\n")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
}
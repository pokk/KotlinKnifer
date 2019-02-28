package com.devrapid.kotlinknifer

import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun Context.closeCurrentTask(activity: AppCompatActivity) =
    (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).appTasks
        .find { it.taskInfo.id == activity.taskId }
        ?.finishAndRemoveTask()

package com.cocobranch.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date

class FilteringWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        try{
            for(i in 0..300){
                Log.i("MyTag", "Filtering $i")
            }

            return Result.success()
        }catch (e:Exception){
            return Result.failure()
        }

    }
}
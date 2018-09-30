package com.sashakhyzhun.androidbarbershopmanagementprototype.notification

import com.evernote.android.job.Job
import com.evernote.android.job.JobRequest
import com.sashakhyzhun.androidbarbershopmanagementprototype.utils.notify
import java.util.*


class SyncJob : Job() {

    companion object {
        const val TAG = "job_tag"
    }

    override fun onRunJob(params: Job.Params): Job.Result {
        // run your job here

        context.notify(
                "You have reservation at current time",
                "haircutEvent",
                "Haircut session"
        )

        return Job.Result.SUCCESS
    }

    fun scheduleJob(regTime: Calendar) {
        println("scheduleJob | regTime=$regTime")

        val calendar = Calendar.getInstance()

        println("scheduleJob | now=${calendar.timeInMillis}")

        val diff = regTime.timeInMillis - calendar.timeInMillis

        println("scheduleJob | diff=$diff")

        JobRequest.Builder(SyncJob.TAG)
                .setExact(diff)
                .build()
                .schedule()
    }

    fun cancelJob(regTime: Calendar) {


    }


}

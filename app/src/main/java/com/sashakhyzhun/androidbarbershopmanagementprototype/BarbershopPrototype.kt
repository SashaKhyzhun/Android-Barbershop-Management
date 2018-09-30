package com.sashakhyzhun.androidbarbershopmanagementprototype

import android.app.Application
import io.paperdb.Paper
import com.evernote.android.job.JobManager
import com.sashakhyzhun.androidbarbershopmanagementprototype.notification.JobCreator

class BarbershopPrototype : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        JobManager.create(this).addJobCreator(JobCreator())
    }


}

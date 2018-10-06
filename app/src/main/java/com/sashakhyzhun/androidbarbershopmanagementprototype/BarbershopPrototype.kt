package com.sashakhyzhun.androidbarbershopmanagementprototype

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.paperdb.Paper
import com.evernote.android.job.JobManager
import com.sashakhyzhun.androidbarbershopmanagementprototype.notification.JobCreator
import io.fabric.sdk.android.Fabric

class BarbershopPrototype : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        JobManager.create(this).addJobCreator(JobCreator())

        Fabric.with(this, CrashlyticsCore(), Crashlytics())


    }


}

package com.sashakhyzhun.androidbarbershopmanagementprototype.notification

import com.evernote.android.job.JobCreator
import android.support.annotation.Nullable
import com.evernote.android.job.Job


class JobCreator : JobCreator {

    @Nullable
    override fun create(tag: String): Job? {
        return when (tag) {
            SyncJob.TAG -> SyncJob()
            else -> null
        }

    }

}

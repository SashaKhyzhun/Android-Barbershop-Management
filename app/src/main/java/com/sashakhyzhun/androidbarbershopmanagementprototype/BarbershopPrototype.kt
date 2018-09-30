package com.sashakhyzhun.androidbarbershopmanagementprototype

import android.app.Application
import io.paperdb.Paper
import com.evernote.android.job.JobManager
import com.sashakhyzhun.androidbarbershopmanagementprototype.notification.JobCreator


/**
 * TODO
 * ALMOST DONE 1. Notification
 * DONE 2. Time for notification
 * DONE 3. Rename 'Accepted & Approved' for 'Pending Reservation'
 * 4. Icon + (Time & date) for pending row
 * 4. Remove button 'Button'
 *
 *
 *
 * Global ideas:
 * DONE 1. Gallery for Barber Profile.
 * DONE 2. Real barber photos
 * DONE 3. Make mock-up as real.
 * 4. Calendar: blurred square for selected time.
 *
 */
class BarbershopPrototype : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        JobManager.create(this).addJobCreator(JobCreator())
    }


}

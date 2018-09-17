package com.sashakhyzhun.androidbarbershopmanagementprototype

import android.app.Application
import io.paperdb.Paper
import timber.log.Timber

/**
 * TODO
 * 1. Notification
 * 2. Time for notification
 * 3. Rename 'Accepted & Approved' for 'Pending Reservation'
 * 4. Icon + (Time & date) for pending row
 * 4. Remove button 'Button'
 *
 *
 *
 * Global ideas:
 * 1. Gallery for Barber Profile.
 * 2. Real barber photos
 * 3. Make mock-up as real.
 * 4. Calendar: blurred square for selected time.
 *
 */
class BarbershopPrototype : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }


}

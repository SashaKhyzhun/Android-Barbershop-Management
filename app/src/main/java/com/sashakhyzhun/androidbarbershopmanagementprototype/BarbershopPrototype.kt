package com.sashakhyzhun.androidbarbershopmanagementprototype

import android.app.Application
import io.paperdb.Paper
import timber.log.Timber

class BarbershopPrototype : Application() {

    override fun onCreate() {
        super.onCreate()
        // qwerty

        Paper.init(this)
    }


}

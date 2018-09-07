package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras

class BarberProfileActivity : AppCompatActivity(), BarberExtras {

    private lateinit var barber: Barber

    private lateinit var tvImage: String
    private lateinit var tvName: String
    private lateinit var tvEmail: String
    private lateinit var tvPhone: String
    private lateinit var tvSex: String
    private lateinit var tvAge: String
    private lateinit var tvCalendar: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barber_profile)

        retrieveExtras()
    }

    private fun retrieveExtras() {
        barber = intent?.extras?.getParcelable(barberKey) ?: Barber()
        try {
            tvImage = barber.profileImage
            tvName = barber.name
            tvEmail = barber.email
            tvPhone = barber.phone
            tvSex = barber.sex
            tvAge = barber.age
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


}
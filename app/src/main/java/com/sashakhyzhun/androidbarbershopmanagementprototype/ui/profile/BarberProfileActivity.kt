package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.monthly.MonthlyActivity

class BarberProfileActivity : AppCompatActivity(), BarberExtras {

    private lateinit var barber: Barber

    private lateinit var tvImage: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvSex: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvCalendar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_barber_profile)

        setupView()

        retrieveExtras()
    }

    private fun setupView() {
        tvImage = findViewById<ImageView>(R.id.profile_image)
        tvName = findViewById<TextView>(R.id.tvName)
        tvEmail = findViewById<TextView>(R.id.tvEmail)
        tvPhone = findViewById<TextView>(R.id.tvPhone)
        tvSex = findViewById<TextView>(R.id.tvSex)
        tvAge = findViewById<TextView>(R.id.tvAge)
        tvCalendar = findViewById<TextView>(R.id.tvCalendar)
        tvCalendar.setOnClickListener {
            startActivity(Intent(this, MonthlyActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun retrieveExtras() {
        barber = intent?.extras?.getParcelable(barberKey) ?: Barber()
        try {
            //tvImage. = barber.profileImage
            tvName.text = barber.name
            tvEmail.text = barber.email
            tvPhone.text = barber.phone
            tvSex.text = "Sex: ${barber.sex}"
            tvAge.text = "Age: ${barber.age}"

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


}
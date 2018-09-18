package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Hair
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.monthly.MonthlyActivity
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile.hairdetail.HairDetailActivity
import kotlin.collections.ArrayList

class BarberProfileActivity : AppCompatActivity(), BarberExtras {

    private lateinit var barber: Barber

    private lateinit var tvImage: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvSex: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvCalendar: TextView
    private lateinit var rvHairs: RecyclerView


    private lateinit var hairs: List<String>


    private var listOfHairs = ArrayList<Hair>()
    private lateinit var hairAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_barber_profile)
        setupView()
        retrieveExtras()

        hairs = arrayListOf("hair_example_1.jpg", "hair_example_2.jpg", "hair_example_3.jpg", "hair_example_4.jpg", "hair_example_5.jpg", "hair_example_6.jpg")
        println("hairs = $hairs")

        for (i in 0 until hairs.size) {
            println(hairs[i])
            listOfHairs.add(Hair("Image #$i", hairs[i]))
        }


        rvHairs = findViewById(R.id.recyclerHairs)
        rvHairs.layoutManager = GridLayoutManager(this, 3)
        rvHairs.setHasFixedSize(true)
        hairAdapter = GalleryAdapter(this, listOfHairs)
        rvHairs.adapter = hairAdapter
        rvHairs.addOnItemTouchListener(RecyclerItemClickListener(this,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(applicationContext, HairDetailActivity::class.java)
                        intent.putParcelableArrayListExtra("data", listOfHairs)
                        intent.putExtra("pos", position)
                        startActivity(intent)
                    }
                }
        ))

    }


    private fun setupView() {
        tvImage = findViewById<ImageView>(R.id.profile_image)
        tvName = findViewById<TextView>(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
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
            tvSex.text = barber.sex
            tvAge.text = "${barber.age} y.o."

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


}
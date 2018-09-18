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

    private var hairs = arrayOf(
            "https://images.unsplash.com/photo-1444090542259-0af8fa96557e?q=80&fm=jpg&w=1080&fit=max&s=4b703b77b42e067f949d14581f35019b",
            "https://images.unsplash.com/photo-1439546743462-802cabef8e97?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1441155472722-d17942a2b76a?q=80&fm=jpg&w=1080&fit=max&s=80cb5dbcf01265bb81c5e8380e4f5cc1",
            "https://images.unsplash.com/photo-1437651025703-2858c944e3eb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1431538510849-b719825bf08b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1434873740857-1bc5653afda8?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1439396087961-98bc12c21176?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1433616174899-f847df236857?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1438480478735-3234e63615bb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1438027316524-6078d503224b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300"
    )


    private var listOfHairs = ArrayList<Hair>()
    private lateinit var hairAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_barber_profile)
        setupView()
        retrieveExtras()

        for (i in 0 until hairs.size) {
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
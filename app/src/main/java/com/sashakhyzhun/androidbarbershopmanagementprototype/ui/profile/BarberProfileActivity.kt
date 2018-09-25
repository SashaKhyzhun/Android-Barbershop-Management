package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Hair
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.monthly.MonthlyActivity
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile.hairdetail.HairDetailActivity
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.email
import org.jetbrains.anko.makeCall
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


    private val hairs: List<Int> = arrayListOf(
            R.drawable.hair_example_1,
            R.drawable.hair_example_2,
            R.drawable.hair_example_3,
            R.drawable.hair_example_4,
            R.drawable.hair_example_5,
            R.drawable.hair_example_6,
            R.drawable.hair_example_7,
            R.drawable.hair_example_8,
            R.drawable.hair_example_9,
            R.drawable.hair_example_10,
            R.drawable.hair_example_11,
            R.drawable.hair_example_12
    )


    private var listOfHairs = ArrayList<Hair>()
    private lateinit var hairAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_barber_profile)

        retrieveExtras()

        setupView()

        println("hairs = $hairs")

        for (i in 0 until hairs.size) {
            println(hairs[i])
            listOfHairs.add(Hair("Image #$i", hairs[i]))
        }


        println("listOfHairs=$listOfHairs")

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


    @SuppressLint("SetTextI18n")
    private fun setupView() {
        val background = findViewById<RelativeLayout>(R.id.layout_background)
        background.background = applicationContext.getDrawable(R.drawable.background)

        tvImage = findViewById<ImageView>(R.id.profile_image)
        Glide.with(this).load(barber.profileImage).apply(RequestOptions().circleCrop()).into(tvImage)

        tvName = findViewById<TextView>(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvEmail.setOnClickListener {
            email(tvEmail.text.toString(),
                    "Haircut Request",
                    "Hello, I want to change my hairstyle")
        }
        tvPhone = findViewById<TextView>(R.id.tvPhone)
        tvPhone.setOnClickListener {
            makeCall(tvPhone.text.toString())
        }
        tvSex = findViewById<TextView>(R.id.tvSex)
        tvAge = findViewById<TextView>(R.id.tvAge)
        tvCalendar = findViewById<TextView>(R.id.tvCalendar)
        tvCalendar.setOnClickListener {
            startActivity(Intent(this, MonthlyActivity::class.java))
        }

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

    private fun retrieveExtras() {
        barber = intent?.extras?.getParcelable(barberKey)
                ?: Barber(0, "", "", "", "", "")
    }


}
package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.barbers

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber

class BarbersAdapter(
    private val ctx: Context,
    private val barbers: List<Barber>,
    private val callback: BarbersOnClick
) : RecyclerView.Adapter<BarbersAdapter.BarbersVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarbersVH =
            BarbersVH(LayoutInflater.from(ctx)
                    .inflate(R.layout.item_barber, parent, false))


    override fun getItemCount(): Int = barbers.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BarbersVH, position: Int) {
        val barber = barbers[position]

        Glide.with(ctx)
                .load(barber.profileImage)
                .apply(RequestOptions().circleCrop())
                .into(holder.imageProfile)

        holder.mainText.text = barber.name
        holder.subText.text = "Hello, I'm ${barber.age}, my phone is ${barber.phone}"
        holder.barberLayout.setOnClickListener {
            callback.onClicked(barber, position)
        }
    }


    class BarbersVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProfile: ImageView = itemView.findViewById(R.id.item_barber_profile_image)
        val mainText: TextView = itemView.findViewById(R.id.item_barber_name)
        val subText: TextView = itemView.findViewById(R.id.item_barber_text3)
        val barberLayout: RelativeLayout = itemView.findViewById(R.id.item_barber_layout)
    }


}
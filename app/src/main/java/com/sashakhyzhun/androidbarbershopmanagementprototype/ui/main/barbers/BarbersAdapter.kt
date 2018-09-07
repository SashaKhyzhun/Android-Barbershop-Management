package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.barbers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.User

class BarbersAdapter(
    private val ctx: Context,
    private val barbers: List<Barber>,
    private val callback: BarbersOnClick
) : RecyclerView.Adapter<BarbersAdapter.BarbersVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarbersVH =
            BarbersVH(LayoutInflater.from(ctx)
                    .inflate(R.layout.item_barber, parent, false))


    override fun getItemCount(): Int = barbers.size


    override fun onBindViewHolder(holder: BarbersVH, position: Int) {
        val barber = barbers[position]

        holder.barbarName.text = barber.name

        holder.barberLayout.setOnClickListener {
            callback.onClicked(barber, position)
        }
    }


    class BarbersVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProfile: ImageView = itemView.findViewById(R.id.item_barber_profile_image)
        val barberLayout: RelativeLayout = itemView.findViewById(R.id.item_barber_layout)
        val barbarName: TextView = itemView.findViewById(R.id.item_barber_name)
        // ...
    }


}
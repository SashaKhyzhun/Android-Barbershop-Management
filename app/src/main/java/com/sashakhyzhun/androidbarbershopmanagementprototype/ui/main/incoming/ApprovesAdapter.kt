package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.incoming

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.IncomingRequest
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author SashaKhyzhun
 * Created on 9/14/18.
 */
internal class ApprovesAdapter(
        private val ctx: Context,
        private val list: List<IncomingRequest>
) : RecyclerView.Adapter<ApprovesAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater
                .from(ctx)
                .inflate(R.layout.item_request, parent, false))
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        val month = SimpleDateFormat("MMM").format(item.regDay.time) //item.regDay.get(Calendar.MONTH)
        val day = item.regDay.get(Calendar.DAY_OF_MONTH)
        val hours = "from " + item.startHour + " till " + item.endHour

        // holder.userImage =
        holder.mainText.text = item.name
        holder.subText.text = "Incoming request on $day'th $month $hours"
    }

    override fun getItemCount(): Int = list.size


    internal inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView = itemView.findViewById(R.id.image_incoming)
        val mainText: TextView = itemView.findViewById(R.id.mainText)
        val subText: TextView = itemView.findViewById(R.id.subText)
    }
}
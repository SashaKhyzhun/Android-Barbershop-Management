package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.requests

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.IncomingRequest
import com.sashakhyzhun.androidbarbershopmanagementprototype.utils.getImage
import java.text.DateFormatSymbols
import java.util.*

/**
 * @author SashaKhyzhun
 * Created on 9/14/18.
 */
internal class IncomingAdapter(
        private val ctx: Context,
        private val users: List<IncomingRequest>
) : RecyclerView.Adapter<IncomingAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater
                .from(ctx)
                .inflate(R.layout.item_incoming, parent, false))
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = users[position]
        val month = DateFormatSymbols().months[item.regDay.get(Calendar.MONTH)]
        val day = item.regDay.get(Calendar.DAY_OF_MONTH)
        val hours = "from " + item.startHour + " till " + item.endHour

        holder.mainText.text = item.name
        holder.subText.text = "Incoming request on $month ${day}th $hours"

        Glide.with(ctx)
                .load(getImage(Random().nextInt(5)))
                .apply(RequestOptions().circleCrop())
                .into(holder.userImage)
    }

    override fun getItemCount(): Int = users.size


    internal inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bg: LinearLayout = itemView.findViewById(R.id.rowFG)
        val userImage: ImageView = itemView.findViewById(R.id.image_incoming)
        val mainText: TextView = itemView.findViewById(R.id.mainText)
        val subText: TextView = itemView.findViewById(R.id.subText)
    }
}
package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.approves

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.UserRequest

import java.util.ArrayList

/**
 * @author SashaKhyzhun
 * Created on 9/14/18.
 */
internal class ApprovesAdapter(
        private val ctx: Context,
        private val list: List<UserRequest>
) : RecyclerView.Adapter<ApprovesAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater
                .from(ctx)
                .inflate(R.layout.item_request, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]

        holder.mainText.text = item.name
        holder.subText.text = item.name


    }

    override fun getItemCount(): Int = list.size


    internal inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mainText: TextView = itemView.findViewById(R.id.mainText)
        var subText: TextView = itemView.findViewById(R.id.subText)
    }
}
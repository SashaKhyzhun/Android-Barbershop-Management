package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Hair
import java.util.*



/**
 * @author SashaKhyzhun
 * Created on 9/18/18.
 */
class GalleryAdapter constructor(
        private val ctx: Context,
        private val  data: ArrayList<Hair>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HairItemHolder(LayoutInflater.from(ctx).inflate(R.layout.item_hair, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(ctx).load(data[position].resId)
                .thumbnail(0.5f)
                .into((holder as HairItemHolder).hairImage)
    }

    internal class HairItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val hairImage: ImageView = view.findViewById(R.id.item_image)
    }

}
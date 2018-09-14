//package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.approves
//
//import android.content.Context
//import android.support.v4.widget.SwipeRefreshLayout
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.RelativeLayout
//import android.widget.TextView
//import com.sashakhyzhun.androidbarbershopmanagementprototype.R
//import com.sashakhyzhun.androidbarbershopmanagementprototype.model.UserRequest
//
///**
// * @author SashaKhyzhun
// * Created on 9/14/18.
// */
//class ApprovesAdapter(
//        private val ctx: Context,
//        private val requests: List<UserRequest>,
//        private val callback: ApprovesCallback
//) : RecyclerView.Adapter<ApprovesAdapter.ApprovesVH>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApprovesVH =
//            ApprovesVH(LayoutInflater.from(ctx)
//                    .inflate(R.layout.item_request, parent, false))
//
//
//    override fun getItemCount(): Int = requests.size
//
//
//    override fun onBindViewHolder(holder: ApprovesVH, position: Int) {
//        val request = requests[position]
//        holder.requestName.text = request.name
//        // ...
//    }
//
//
//    class ApprovesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val mainText = itemView.findViewById<TextView>(R.id.mainText)
//        val subText = itemView.findViewById<View>(R.id.subText) as TextView
//
//    }
//
//
//}
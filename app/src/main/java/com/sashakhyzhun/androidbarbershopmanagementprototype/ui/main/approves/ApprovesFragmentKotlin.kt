//package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.approves
//
//import android.content.Context
//import android.graphics.Canvas
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.support.v7.widget.helper.ItemTouchHelper
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
//import com.sashakhyzhun.androidbarbershopmanagementprototype.R
//import com.sashakhyzhun.androidbarbershopmanagementprototype.data.PaperORM
//import com.sashakhyzhun.androidbarbershopmanagementprototype.model.UserRequest
//import timber.log.Timber
//
//class ApprovesFragmentKotlin : Fragment(), ApprovesCallback {
//
//    private lateinit var requests: ArrayList<UserRequest>
//    private lateinit var adapter: ApprovesAdapter
//    private lateinit var recycler: RecyclerView
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Timber.d("")
//        requests = arrayListOf()
//        adapter = ApprovesAdapter(context!!, requests, this)
//    }
//
//    override fun onCreateView(inf: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
//        val view = inf.inflate(R.layout.fragment_approves, group, false)
//        Timber.d("")
//
//        recycler = view.findViewById(R.id.recyclerViewRequests)
//
//        initView(view)
//        updateUI()
//        return view
//    }
//
//
//    private fun updateUI() {
//        recycler.adapter = adapter
//        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//    }
//
//
//    override fun onResume() {
//        super.onResume()
//        Timber.d("")
//
//        val all = PaperORM.getBooksById()
//        requests.clear()
//        requests.addAll(all)
//        updateUI()
//    }
//
//    override fun onSwipeLeft() {
//        println("Swiped left")
//    }
//
//    override fun onSwipeRight() {
//        println("Swiped right")
//    }
//
//
//}
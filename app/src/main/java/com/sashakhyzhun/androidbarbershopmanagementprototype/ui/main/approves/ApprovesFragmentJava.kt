package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.approves

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.data.PaperORM
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.UserRequest

import java.util.ArrayList

import timber.log.Timber

/**
 * @author SashaKhyzhun
 * Created on 9/14/18.
 */
class ApprovesFragmentJava : Fragment() {

    private lateinit var onTouchListener: RecyclerTouchListener
    private lateinit var requests: ArrayList<UserRequest>
    private lateinit var adapter: ApprovesAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("")
        requests = ArrayList()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_approves, container, false)

        val all = PaperORM.getBooksById()
        println("all size = " + all.size)
        requests.addAll(all)

        adapter = ApprovesAdapter(context!!, requests)
        recycler = view.findViewById(R.id.recyclerViewRequests)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        onTouchListener = RecyclerTouchListener(activity, recycler)
        onTouchListener.setIndependentViews(R.id.rowButton)
                .setViewsToFade(R.id.rowButton)
                .setClickable(object : RecyclerTouchListener.OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        Toast.makeText(context, "On Row Clicked", Toast.LENGTH_SHORT).show()
                    }

                    override fun onIndependentViewClicked(independentViewID: Int, position: Int) {
                        Toast.makeText(context, "Independent View Clicked", Toast.LENGTH_SHORT).show()
                    }
                })
                .setLongClickable(true) { position -> }
                .setSwipeOptionViews(R.id.add, R.id.edit, R.id.change)
                .setSwipeable(R.id.rowFG, R.id.rowBG) { viewID, position ->
                    when (viewID) {
                        R.id.add -> Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
                        R.id.edit -> Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
                        R.id.change -> Toast.makeText(context, "Change", Toast.LENGTH_SHORT).show()
                    }
                }

        return view
    }


    override fun onResume() {
        super.onResume()
        recycler.addOnItemTouchListener(onTouchListener)
    }

    override fun onPause() {
        super.onPause()
        recycler.removeOnItemTouchListener(onTouchListener)
    }


}

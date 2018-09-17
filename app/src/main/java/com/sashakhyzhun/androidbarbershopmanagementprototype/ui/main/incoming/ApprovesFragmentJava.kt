package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.incoming

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.data.PaperConst
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.AcceptedRequest
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.IncomingRequest
import io.paperdb.Paper

import java.util.ArrayList

import timber.log.Timber

/**
 * @author SashaKhyzhun
 * Created on 9/14/18.
 */
class ApprovesFragmentJava : Fragment() {

    private lateinit var onTouchListener: RecyclerTouchListener
    private lateinit var requests: ArrayList<IncomingRequest>
    private lateinit var adapter: ApprovesAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("")
        requests = ArrayList()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_approves, container, false)

        val incomingList: MutableList<IncomingRequest> = Paper.book().read(PaperConst.incomingList, arrayListOf())
        val acceptedList: MutableList<AcceptedRequest> = Paper.book().read(PaperConst.acceptedList, arrayListOf())

        println("all incoming size = " + incomingList.size)
        println("all accepted size = " + acceptedList.size)
        requests.addAll(incomingList)

        adapter = ApprovesAdapter(context!!, requests)
        recycler = view.findViewById(R.id.recyclerViewRequests)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        onTouchListener = RecyclerTouchListener(activity, recycler)
        onTouchListener
                //.setIndependentViews(R.id.rowButton)
                //.setViewsToFade(R.id.rowButton)
                .setLongClickable(true) { position -> }
                .setSwipeOptionViews(R.id.layout_accept, R.id.layout_cancel)
                .setSwipeable(R.id.rowFG, R.id.rowBG) { viewID, position ->
                    when (viewID) {
                        R.id.layout_accept -> {
                            val req = requests[position]

                            acceptedList.add(AcceptedRequest(req.name, req.regDay, req.startHour, req.endHour))
                            Paper.book().write(PaperConst.acceptedList, acceptedList)

                            incomingList.removeAt(position)
                            Paper.book().write(PaperConst.incomingList, incomingList)

                            requests.removeAt(position)
                            updateRV()
                        }
                        R.id.layout_cancel -> {
                            incomingList.removeAt(position)
                            requests.removeAt(position)

                            updateRV()
                        }
                    }
                }

        return view
    }

    private fun updateRV() {
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
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

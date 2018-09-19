package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.barbers

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.profile.BarberProfileActivity
import timber.log.Timber


class BarbersFragment : Fragment(), BarbersOnClick, BarberExtras {

    companion object {
        fun newInstance(): BarbersFragment = BarbersFragment()
    }

    private lateinit var adapter: BarbersAdapter
    private lateinit var barbers: MutableList<Barber>
    private lateinit var rvBarber: RecyclerView


    override fun onCreateView(inf: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        val view = inf.inflate(R.layout.fragment_barbers, group, false)
        Timber.d("")

        barbers = mutableListOf()
        barbers.fillWithMock()

        adapter = BarbersAdapter(context!!, barbers, this)

        rvBarber = view.findViewById(R.id.recyclerViewBarbers)
        rvBarber.adapter = adapter
        rvBarber.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return view
    }


    override fun onClicked(barber: Barber, position: Int) {
        val intent = Intent(context, BarberProfileActivity::class.java)
        intent.putExtra(barberKey, barber)
        startActivity(intent)
    }


    private fun MutableList<Barber>.fillWithMock() {
        add(Barber(R.drawable.barber_1, "Jame M. Briscoe", "JameMBriscoe@armyspy.com", "612-668-7559", "Male", "37"))
        add(Barber(R.drawable.barber_2, "James Allford", "JamesAllford@gmail.com", "209-388-0335", "Male", "24"))
        add(Barber(R.drawable.barber_3, "Robert Hunter", "RobertGHunter@rhyta.com", "419-458-0192", "Male", "26"))
        add(Barber(R.drawable.barber_4, "Scott Bush", "Scott Bush@dayrep.com", "276-773-6988", "Male", "31"))
    }


}
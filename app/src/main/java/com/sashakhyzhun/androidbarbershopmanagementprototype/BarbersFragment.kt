package com.sashakhyzhun.androidbarbershopmanagementprototype

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import timber.log.Timber

class BarbersFragment : Fragment() {

    companion object {
        fun newInstance(): BarbersFragment = BarbersFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Timber.d("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("")
    }

    override fun onCreateView(inf: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        val view = inf.inflate(R.layout.fragment_barbers, group, false)
        Timber.d("")
        return view
    }

    override fun onStart() {
        super.onStart()
        Timber.d("")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("")
    }


}
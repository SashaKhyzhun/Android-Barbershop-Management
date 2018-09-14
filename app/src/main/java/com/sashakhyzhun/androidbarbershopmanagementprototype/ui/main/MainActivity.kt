package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.approves.ApprovesFragmentJava
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.barbers.BarbersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var selectedFragment: Fragment
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        // Navigation bottom bar action handling
        navigation.setOnNavigationItemSelectedListener(this)

        // Manually displaying the first fragment - one time only
        handleFragmentLoading(BarbersFragment())
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_barbers -> {
                selectedFragment = BarbersFragment()
            }
            R.id.navigation_approves -> {
                selectedFragment = ApprovesFragmentJava()
            }
        }

        handleFragmentLoading(selectedFragment)
        return true
    }



    private fun handleFragmentLoading(fragment: Fragment) {
        transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

}

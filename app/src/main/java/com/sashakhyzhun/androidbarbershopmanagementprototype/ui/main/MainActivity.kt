package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.NotificationActions
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.incoming.ApprovesFragment
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.barbers.BarbersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        NotificationActions {

    private lateinit var selectedFragment: Fragment
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        // Permission for storage
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

        // Handling notification action
        val isNotifAction = intent?.extras?.getBoolean(notificationAction) ?: false

        // Manually displaying the first fragment - one time only
        if (isNotifAction.not()) {
            handleFragmentLoading(BarbersFragment())
        } else {
            handleFragmentLoading(ApprovesFragment())
            navigation.menu.findItem(R.id.navigation_approves).isChecked = true
        }


        // Navigation bottom bar action handling
        navigation.setOnNavigationItemSelectedListener(this)

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_barbers -> { selectedFragment = BarbersFragment() }
            R.id.navigation_approves -> { selectedFragment = ApprovesFragment() }
        }

        handleFragmentLoading(selectedFragment)
        return true
    }



    private fun handleFragmentLoading(fragment: Fragment) {
        transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
        println("fragmentID = ${fragment.id}")

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1 && grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // granted
        } else {
            // not granted
        }
    }

}

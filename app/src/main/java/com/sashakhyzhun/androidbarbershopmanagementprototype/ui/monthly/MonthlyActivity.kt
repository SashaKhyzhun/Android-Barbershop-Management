package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.monthly

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marcohc.robotocalendar.RobotoCalendarView
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper




class MonthlyActivity : AppCompatActivity(), RobotoCalendarView.RobotoCalendarListener {

    private lateinit var calendarView: RobotoCalendarView

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    private val date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly)

        val millis = System.currentTimeMillis()
        val date = Date(millis)

        calendarView = findViewById(R.id.calendarView)
        calendarView.setDate(date)
        calendarView.setRobotoCalendarListener(this)

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onDayClick(date: Date) {
        Toast.makeText(this, "onDayClick: $date", Toast.LENGTH_SHORT).show()
    }

    override fun onDayLongClick(date: Date) {
        Toast.makeText(this, "onDayLongClick: $date", Toast.LENGTH_SHORT).show()
    }

    override fun onRightButtonClick() {
        Toast.makeText(this, "onRightButtonClick!", Toast.LENGTH_SHORT).show()
    }

    override fun onLeftButtonClick() {
        Toast.makeText(this, "onLeftButtonClick!", Toast.LENGTH_SHORT).show()
    }




}
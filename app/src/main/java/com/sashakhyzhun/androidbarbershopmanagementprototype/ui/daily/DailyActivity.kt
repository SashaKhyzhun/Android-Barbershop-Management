package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.daily

import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alamkanak.weekview.DateTimeInterpreter
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import java.util.*

class DailyActivity : AppCompatActivity(), BarberExtras,
    WeekView.EventClickListener, WeekView.EventLongPressListener {

    private lateinit var date: Date
    private lateinit var mWeekView: WeekView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        date = intent?.extras?.get(dateKey) as Date
        println("extras: $date")


        // Get a reference for the week view in the layout.
        mWeekView = findViewById(R.id.weekView)

        // Disabling horizontal scrolling
        mWeekView.xScrollingSpeed = 0.0F

        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(this)

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.monthChangeListener = MonthLoader.MonthChangeListener { _, _ -> mutableListOf() }

        // Set long press listener for events.
        mWeekView.eventLongPressListener = this
    }


    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventClick | event:$event, eventRect:$eventRect")
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventLongPress | event:$event, eventRect:$eventRect")
    }
}
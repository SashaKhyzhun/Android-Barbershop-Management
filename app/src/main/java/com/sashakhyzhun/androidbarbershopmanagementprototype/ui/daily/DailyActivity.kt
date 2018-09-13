package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.daily

import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import java.util.*
import kotlin.collections.ArrayList

open class DailyActivity : AppCompatActivity(), BarberExtras,
    WeekView.EventClickListener, WeekView.EventLongPressListener {

    private lateinit var extraDate: Date
    private lateinit var calendar: Calendar
    private lateinit var mWeekView: WeekView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        extraDate = intent?.extras?.get(dateKey) as Date
        calendar = Calendar.getInstance()
        calendar.time = extraDate


        // Get a reference for the week view in the layout.
        mWeekView = findViewById(R.id.weekView)
        // Set selected date
        mWeekView.goToDate(calendar)
        // mWeekView.goToToday()

        // Disabling horizontal scrolling
        mWeekView.xScrollingSpeed = 0.0F

        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(this)

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.monthChangeListener = MonthLoader.MonthChangeListener { year, month ->
            // Populate the week view with some events.
            getEvents()
        }
        
        // Notify this shit about updates
        mWeekView.notifyDatasetChanged()

        // Set long press listener for events.
        mWeekView.eventLongPressListener = this

        mWeekView.emptyViewClickListener = WeekView.EmptyViewClickListener {
            Toast.makeText(this, "Tap: ${it.time}", Toast.LENGTH_SHORT).show()
        }

        mWeekView.emptyViewLongPressListener = WeekView.EmptyViewLongPressListener {
            Toast.makeText(this, "Long Tap: ${it.time}", Toast.LENGTH_SHORT).show()
        }


    }

    /**
     * @param begin - time in millis
     * @param end - time in millis
     */
    private fun getEvents(): MutableList<out WeekViewEvent> {
        val weekViewList: MutableList<WeekViewEvent > = mutableListOf()

        val startTime: Calendar = Calendar.getInstance()
        startTime.time = Date(1536838555000)

        val endTime: Calendar = Calendar.getInstance()
        endTime.time = (Date(1536842155000))

        val viewEvent = WeekViewEvent(1, "haircut session #1", startTime, endTime)
        weekViewList.add(viewEvent)

        return weekViewList
    }


    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventClick | event:$event, eventRect:$eventRect")
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventLongPress | event:$event, eventRect:$eventRect")
    }




}
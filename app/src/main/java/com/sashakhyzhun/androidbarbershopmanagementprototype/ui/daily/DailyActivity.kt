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

open class DailyActivity : AppCompatActivity(), BarberExtras,
    WeekView.EventClickListener, WeekView.EventLongPressListener {

    private lateinit var extraDate: Date
    private lateinit var calendar: Calendar
    private lateinit var mWeekView: WeekView
    private var extraIsFreeDay: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        extraDate = intent?.extras?.get(dateKey) as Date
        extraIsFreeDay = intent?.extras?.get(freeDayKey) as Boolean

        println("is busy day: $extraIsFreeDay")

        // Initiate calendar
        calendar = Calendar.getInstance()
        // set current day in millis
        calendar.time = extraDate
        // Get a reference for the week view in the layout.
        mWeekView = findViewById(R.id.weekView)
        // Set selected date
        mWeekView.goToDate(calendar)
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

    private fun getEvents(): MutableList<out WeekViewEvent> {
        val weekViewList: MutableList<WeekViewEvent > = mutableListOf()

        var start = Calendar.getInstance()
        start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 9, 0, 0)

        var end = Calendar.getInstance()
        end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(1, "haircut session #1", start, end))


        start = Calendar.getInstance()
        start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 11, 15, 0)

        end = Calendar.getInstance()
        end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(2, "haircut session #2", start, end))


        start = Calendar.getInstance()
        start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 13, 15, 0)

        end = Calendar.getInstance()
        end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(3, "haircut session #3", start, end))

        start = Calendar.getInstance()
        start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 15, 15, 0)

        end = Calendar.getInstance()
        end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(4, "haircut session #4", start, end))


        return weekViewList
    }

/*
    private fun getEvents(): MutableList<out WeekViewEvent> {
        val weekViewList: MutableList<WeekViewEvent > = mutableListOf()


        val calStart = Calendar.getInstance()
        val calEnd = calStart.clone() as Calendar
        calStart.time = extraDate


         //9 - 11
        calStart.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), 9, 0, 0)

        calEnd.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), calStart.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(1, "haircut session #1", calStart, calEnd))

         //11 - 13
        calStart.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), 11, 0, 0)

        calEnd.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), calStart.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)
        weekViewList.add(WeekViewEvent(2, "haircut session #2", calStart, calEnd))

         //13 - 15
        calStart.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), 13, 0, 0)

        calEnd.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), calStart.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)
        weekViewList.add(WeekViewEvent(3, "haircut session #3", calStart, calEnd))


         //15 - 17
        calStart.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), 15, 0, 0)

        calEnd.set(calStart.get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                calStart.get(Calendar.DAY_OF_MONTH), calStart.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)
        weekViewList.add(WeekViewEvent(4, "haircut session #4", calStart, calEnd))


        return weekViewList
    }
*/


    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventClick | event:$event, eventRect:$eventRect")
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventLongPress | event:$event, eventRect:$eventRect")
    }




}
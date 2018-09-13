package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.daily

import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.alamkanak.weekview.WeekViewLoader
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import java.util.*
import kotlin.collections.ArrayList

open class DailyActivity : AppCompatActivity(), BarberExtras,
    WeekView.EventClickListener, WeekView.EventLongPressListener {

    private lateinit var extraDate: Date
    private lateinit var calendar: Calendar
    private lateinit var mWeekView: WeekView

    private var events: ArrayList<WeekViewEvent> = arrayListOf()

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
            val startTime = Calendar.getInstance()
            startTime.set(Calendar.HOUR_OF_DAY, 3)
            startTime.set(Calendar.MINUTE, 0)
            startTime.set(Calendar.MONTH, month - 1)
            startTime.set(Calendar.YEAR, year)
            val endTime = startTime.clone() as Calendar
            endTime.add(Calendar.HOUR, 4)
            endTime.set(Calendar.MONTH, month - 1)
            val event = WeekViewEvent(1, "qwerty", startTime, endTime)
            event.color = resources.getColor(R.color.event_color_01)
            events.add(event)
            mutableListOf()
        }


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


    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventClick | event:$event, eventRect:$eventRect")
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventLongPress | event:$event, eventRect:$eventRect")
    }

    private fun testTas(): List<WeekViewEvent> {
        val startTime = Calendar.getInstance()
        startTime.time = Date(1536819683000)

        val endTime = Calendar.getInstance()
        startTime.time = Date(1537063502000)

        val event = WeekViewEvent(1, "name", startTime, endTime)
        event.color = resources.getColor(R.color.event_color_01)
        events.add(event)

        return events
    }

//    private class DayLoader : WeekViewLoader {
//
//        private lateinit var mDayChangeListener: DayChangesListener
//
//        override fun toWeekViewPeriodIndex(instance: Calendar?): Double {
//            return (instance?.timeInMillis!! / (1000.0 * 60 * 60 * 24))
//        }
//
//        override fun onLoad(periodIndex: Int): List<out WeekViewEvent> {
//            val day = Calendar.getInstance()
//            day.timeInMillis = ((periodIndex * (1000 * 60 * 60 * 24)).toLong())
//
//            return mDayChangeListener.onDayChange(
//                    day.get(Calendar.YEAR),
//                    day.get(Calendar.MONTH),
//                    day.get(Calendar.DAY_OF_MONTH)
//            )
//        }
//        interface DayChangesListener {
//            fun onDayChange(year: Int, month: Int, dayOfMonth: Int): List<out WeekViewEvent>
//        }
//    }

}
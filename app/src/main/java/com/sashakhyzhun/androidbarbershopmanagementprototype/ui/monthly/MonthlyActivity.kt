package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.monthly

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.CalendarView
import com.applikeysolutions.cosmocalendar.selection.SingleSelectionManager
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays
import com.applikeysolutions.cosmocalendar.view.MonthView
import com.marcohc.robotocalendar.RobotoCalendarView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_SINGLE
import com.prolificinteractive.materialcalendarview.format.DayFormatter
import com.riontech.calendar.CustomCalendar
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import kotlinx.android.synthetic.main.activity_monthly.*
import java.util.*
import com.riontech.calendar.utils.CalendarUtils.getEventsDescription
import com.riontech.calendar.utils.CalendarUtils.getEVENTS
import com.riontech.calendar.dao.dataAboutDate
import com.riontech.calendar.utils.CalendarUtils.getNAMES
import com.riontech.calendar.dao.EventData
import com.riontech.calendar.utils.CalendarUtils
import org.threeten.bp.LocalDate
import com.sashakhyzhun.androidbarbershopmanagementprototype.R.id.calendarView
import java.text.SimpleDateFormat


class MonthlyActivity : AppCompatActivity() {

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





//        val arr = arrayOf("2018-09-15", "2018-09-19", "2018-09-20", "2018-09-21", "2018-10-01", "2018-10-05")
//        for (i in 0..4) {
//            val eventCount = 3
//            calendarView.addAnEvent(arr[i], eventCount, getEventDataList(eventCount))
//        }

    }

//    fun getEventDataList(count: Int): ArrayList<EventData> {
//        val eventDataList = arrayListOf<EventData>()
//
//        for (i in 0 until count) {
//            val dateData = EventData()
//            val dataAboutDates = arrayListOf<dataAboutDate>()
//
//            dateData.section = CalendarUtils.getNAMES()[Random().nextInt(CalendarUtils.getNAMES().size)]
//            val dataAboutDate = dataAboutDate()
//
//            val index = Random().nextInt(CalendarUtils.getEVENTS().size)
//
//            dataAboutDate.title = CalendarUtils.getEVENTS()[index]
//            dataAboutDate.subject = CalendarUtils.getEventsDescription()[index]
//            dataAboutDates.add(dataAboutDate)
//
//            dateData.data = dataAboutDates
//            eventDataList.add(dateData)
//        }
//
//        return eventDataList
//    }



}
package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.daily

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import com.sashakhyzhun.androidbarbershopmanagementprototype.data.PaperConst
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.AcceptedRequest
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.IncomingRequest
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import com.sashakhyzhun.androidbarbershopmanagementprototype.utils.CustomUI
import com.sashakhyzhun.androidbarbershopmanagementprototype.utils.notifyAboutNewRequest
import io.paperdb.Paper
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

open class DailyActivity : AppCompatActivity(), BarberExtras,
    WeekView.EventClickListener, WeekView.EventLongPressListener {

    private lateinit var extraDate: Date
    private lateinit var calendar: Calendar
    private lateinit var mWeekView: WeekView
    private lateinit var ctx: Context
    private var extraIsFreeDay: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        ctx = this

        extraDate = intent?.extras?.get(dateKey) as Date
        extraIsFreeDay = intent?.extras?.get(freeDayKey) as Boolean
        println("extraDate=$extraDate")
        println("extraIsFreeDay=$extraIsFreeDay")

        // Initiate calendar
        calendar = Calendar.getInstance()
        // set current day in millis
        calendar.time = extraDate
        // Get a reference for the week view in the layout.
        mWeekView = findViewById(R.id.weekView)
        // Set selected date
        mWeekView.goToDate(calendar)
        // Make out calendar smaller
        mWeekView.hourHeight = 100
        // Disabling horizontal scrolling
        mWeekView.xScrollingSpeed = 0.0F
        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(this)
        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.monthChangeListener = MonthLoader.MonthChangeListener { _, _ ->
            // Populate the week view with some events.
            if (extraIsFreeDay.not()) getMockEvents() else getAcceptedEvents()
        }

        // Notify this shit about updates
        mWeekView.notifyDatasetChanged()
        // Set long press listener for events.
        mWeekView.eventLongPressListener = this

        mWeekView.emptyViewClickListener = WeekView.EmptyViewClickListener {
            //Toast.makeText(this, "Tap: ${it.time}", Toast.LENGTH_SHORT).show()
            it.set(it.get(Calendar.YEAR),
                    it.get(Calendar.MONTH),
                    it.get(Calendar.DAY_OF_MONTH),
                    it.get(Calendar.HOUR_OF_DAY),
                    0,
                    0
            )
            CustomUI.createAlertDialog(ctx,
                    R.string.dialog_title,
                    "Confirm registration from "
                            + "${it.get(Calendar.HOUR_OF_DAY)}:${it.get(Calendar.MINUTE)}0 till "
                            + "${it.get(Calendar.HOUR_OF_DAY) +1 }:${it.get(Calendar.MINUTE)}0",
                    R.string.button_confirm,
                    R.string.button_cancel, null) {

                val incomingRequest = IncomingRequest(
                        name = getRandomName(Random().nextInt(10)),
                        regDay = it,
                        startHour = it.get(Calendar.HOUR_OF_DAY),
                        endHour = it.get(Calendar.HOUR_OF_DAY) + 1)

                val incomingList: List<IncomingRequest> = arrayListOf(incomingRequest)

                Paper.book().write(PaperConst.incomingList, incomingList)
                val notifText = generateNotificationText(incomingRequest)

                notifyAboutNewRequest(text = notifText)

            }
        }

        mWeekView.emptyViewLongPressListener = WeekView.EmptyViewLongPressListener {
            //Toast.makeText(this, "Long Tap: ${it.time}", Toast.LENGTH_SHORT).show()
            //val et = CustomUI.createEditText(ctx, null, R.string.edit_text_hint)
        }


    }

    // val month = SimpleDateFormat("MMM").format(item.regDay.time)
    @SuppressLint("SimpleDateFormat")
    private fun generateNotificationText(it: IncomingRequest): String {
        return "${it.name} on ${DateFormatSymbols().months[it.regDay.get(Calendar.MONTH)]} " +
                "${it.regDay.get(Calendar.DAY_OF_MONTH)}th " +
                "from ${it.regDay.get(Calendar.HOUR_OF_DAY)}:${it.regDay.get(Calendar.MINUTE)}0 " +
                "till ${it.regDay.get(Calendar.HOUR_OF_DAY) +1 }:${it.regDay.get(Calendar.MINUTE)}0"
    }

    private fun getMockEvents(): MutableList<out WeekViewEvent> {
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
                calendar.get(Calendar.DAY_OF_MONTH), 11, 5, 0)

        end = Calendar.getInstance()
        end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(2, "haircut session #2", start, end))


        start = Calendar.getInstance()
        start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 13, 5, 0)

        end = Calendar.getInstance()
        end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(3, "haircut session #3", start, end))

        start = Calendar.getInstance()
        start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 15, 5, 0)

        end = Calendar.getInstance()
        end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY) + 2, 0, 0)

        weekViewList.add(WeekViewEvent(4, "haircut session #4", start, end))

        return weekViewList
    }

    private fun getAcceptedEvents(): MutableList<out WeekViewEvent> {
        val weekViewList: MutableList<WeekViewEvent > = mutableListOf()
        val saved: List<AcceptedRequest> = Paper.book().read(PaperConst.acceptedList, arrayListOf())
        println("saved size: ${saved.size}")
        saved.forEachIndexed { index, it ->
            val start = Calendar.getInstance()
            start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), it.startHour, 0, 0)

            val end = Calendar.getInstance()
            end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), it.endHour, 0, 0)


            weekViewList.add((WeekViewEvent(index + 4L, it.name, start, end)))
        }

        return weekViewList
    }

    private fun getRandomName(i: Int): String {
        return when (i) {
            0 -> "Michal"
            1 -> "Bob"
            2 -> "Sarah"
            3 -> "David"
            4 -> "Jennifer"
            5 -> "Liza"
            6 -> "Mike"
            7 -> "Andrew"
            8 -> "Jessica"
            9 -> "Olivia"
            else -> ""
        }
    }


    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventClick | event:$event, eventRect:$eventRect")
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventLongPress | event:$event, eventRect:$eventRect")
    }




}
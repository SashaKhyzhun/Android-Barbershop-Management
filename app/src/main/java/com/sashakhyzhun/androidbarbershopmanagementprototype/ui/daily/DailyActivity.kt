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
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.NotificationActions
import com.sashakhyzhun.androidbarbershopmanagementprototype.utils.*
import io.paperdb.Paper
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

open class DailyActivity : AppCompatActivity(), BarberExtras,
        WeekView.EventClickListener,
        WeekView.EventLongPressListener,
        NotificationActions {

    private lateinit var extraDate: Date
    private lateinit var calendar: Calendar
    private lateinit var mWeekView: WeekView
    private lateinit var ctx: Context
    private lateinit var dfExtraDate: String
    private var extraIsFreeDay: Boolean = false


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        ctx = this
        extraDate = intent?.extras?.get(dateKey) as Date
        extraIsFreeDay = intent?.extras?.get(freeDayKey) as Boolean
        val now = System.currentTimeMillis()

        dfExtraDate = SimpleDateFormat("EEEE, MMMM d, yyyy").format(extraDate)
        println("extraDate=$extraDate")
        println("extraIsFreeDay=$extraIsFreeDay")
        println("dfExtraDate=$dfExtraDate")

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
            if (extraIsFreeDay.not()) { // if it's not "5th, 10th, 15th, 20th, 25th or 30th" day
                getMockEvents(calendar)
            } else {
                getAcceptedEvents()
            }
        }
        // Notify this shit about updates
        mWeekView.notifyDatasetChanged()
        // Set long press listener for events.
        mWeekView.eventLongPressListener = this
        // Set on click listener for events

        if (extraIsFreeDay && calendar.time.time >= now) {
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
                                + "${it.get(Calendar.HOUR_OF_DAY) + 1}:${it.get(Calendar.MINUTE)}0",
                        R.string.button_confirm,
                        R.string.button_cancel, null) {

                    val incomingRequest = IncomingRequest(
                            name = getName(Random().nextInt(16)),
                            regDay = it,
                            startHour = it.get(Calendar.HOUR_OF_DAY),
                            endHour = it.get(Calendar.HOUR_OF_DAY) + 1,
                            photo = getImage(Random().nextInt(16)))

                    val incomingList: List<IncomingRequest> = arrayListOf(incomingRequest)

                    Paper.book().write(PaperConst.incomingList, incomingList)
                    val notifText = generateNotificationText(incomingRequest)

                    notifyAboutNewRequest(text = notifText, extraKey = notificationAction)

                }
            }
        } else {
            mWeekView.setBackgroundResource(R.color.week_view_bg)
        }

        // Set on long click for empty view
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


    @SuppressLint("SimpleDateFormat")
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
                    calendar.get(Calendar.DAY_OF_MONTH), it.startHour, 58, 0)


            val df = SimpleDateFormat("EEEE, MMMM d, yyyy").format(it.regDay.time)

            if (dfExtraDate == df) {
                weekViewList.add((WeekViewEvent(index + 4L, it.name, start, end)))
            }
        }

        return weekViewList
    }



    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventClick | event:$event, eventRect:$eventRect")
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {
        println("onEventLongPress | event:$event, eventRect:$eventRect")
    }




}
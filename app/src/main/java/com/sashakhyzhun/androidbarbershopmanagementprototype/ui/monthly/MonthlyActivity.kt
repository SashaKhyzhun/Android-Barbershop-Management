package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.monthly

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marcohc.robotocalendar.RobotoCalendarView
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import java.util.*
import android.widget.Toast
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper




class MonthlyActivity : AppCompatActivity(), RobotoCalendarView.RobotoCalendarListener {

    companion object {
        const val oneDay: Int = 24 * 60 * 60 * 1000
    }

    private lateinit var calendarView: RobotoCalendarView

    private val calendar = Calendar.getInstance()
    private val now = System.currentTimeMillis()
    private val today = Date(now)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly)

        calendarView = findViewById(R.id.calendarView)
        calendarView.setDate(today)
        calendarView.setRobotoCalendarListener(this)

        loadCurrentMonth()

    }

    private fun loadCurrentMonth() {
        val year = getCurrentYear()
        println("year: $year")
        val month = getCurrentMonth()
        println("month: $month")
        val day = getCurrentDay()
        println("day: $day")


        val daysInMonth = getDaysInMonth(year, month)
        println("daysInMonth: $daysInMonth")

        val firstDayMillis = getFirstDayOfMonthInMillis(year, month)

        calendarView.markCircleImage1(Date(firstDayMillis))


        for (i in 0 until daysInMonth) {
            print("i=$i")
//            if (d % 10 == 0) { continue } // skip every 10, 20, 30 day
//            calendarView.markCircleImage1(Date(firstDayMillis + oneDay))
        }


    }

    private fun getCurrentYear(): Int = calendar.get(Calendar.YEAR)
    private fun getCurrentMonth(): Int = calendar.get(Calendar.MONTH) + 1
    private fun getCurrentDay(): Int = calendar.get(Calendar.DAY_OF_MONTH)

    private fun getDaysInMonth(year: Int, month: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 12)
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun getFirstDayOfMonthInMillis(year: Int, month: Int): Long {
        val cal = GregorianCalendar(year, month - 1, 1)
        cal.timeZone = TimeZone.getDefault()
        return cal.time.time
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
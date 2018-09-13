package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.monthly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marcohc.robotocalendar.RobotoCalendarView
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import java.util.*
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.common.BarberExtras
import com.sashakhyzhun.androidbarbershopmanagementprototype.ui.daily.DailyActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import kotlin.collections.ArrayList

class MonthlyActivity : AppCompatActivity(), BarberExtras, RobotoCalendarView.RobotoCalendarListener {

    companion object {
        const val oneDay: Long = 24 * 60 * 60 * 1000
    }

    private lateinit var calendarView: RobotoCalendarView
    private var monthIndex: Int = 0
    private val calendar = Calendar.getInstance()
    private val now = System.currentTimeMillis()
    private val today = Date(now)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly)

        calendarView = findViewById(R.id.calendarView)
        calendarView.setDate(today)
        calendarView.setRobotoCalendarListener(this)

        monthIndex = getCurrentMonth()
        val currentMonthData = loadMonthlyData()
        fillCalendar(currentMonthData)
    }


    private fun loadMonthlyData(): ArrayList<Long> {
        val year = getCurrentYear()
        val daysInMonth = getDaysInMonth(year, monthIndex)
        val firstDayMillis = getFirstDayOfMonthInMillis(year, monthIndex)

        var additionalDay: Long = 0
        val timeStamps: ArrayList<Long> = arrayListOf()
        for (i in 0 until daysInMonth) {
            val timestamp = firstDayMillis + additionalDay
            additionalDay += oneDay
            timeStamps.add(timestamp)
        }

        return timeStamps
    }


    private fun fillCalendar(monthlyData: ArrayList<Long>) {
        for (i in 0 until monthlyData.size) {
            if (i == 4 || i == 9 || i == 14 || i == 19 || i == 24) { continue }
            calendarView.markCircleImage1(Date(monthlyData[i]))
        }
    }


    private fun getCurrentYear(): Int = calendar.get(Calendar.YEAR)

    /**
     * Hows it work:
     * we get current month.
     * if we need next one we need to put positive number
     * if we need previous one we need to put negative number
     */
    private fun getCurrentMonth(offset: Int = 0): Int = calendar.get(Calendar.MONTH) + 1 + offset


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


    private fun isMultipleOfFive(date: Date): Boolean {
        val cal = Calendar.getInstance()
        cal.time = date //val year = cal.get(Calendar.YEAR); val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        return (day == 5 || day == 10 || day == 15 || day == 20 || day == 25)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


    override fun onDayClick(date: Date) {
        val intent = Intent(this, DailyActivity::class.java)
        intent.putExtra(dateKey, date)
        intent.putExtra(freeDayKey, isMultipleOfFive(date))
        startActivity(intent)
    }


    override fun onDayLongClick(date: Date) {

    }


    override fun onLeftButtonClick() {
        //Toast.makeText(this, "onLeftButtonClick!", Toast.LENGTH_SHORT).show()
        monthIndex -= 1
        val previousMonthData = loadMonthlyData()
        fillCalendar(previousMonthData)

    }


    override fun onRightButtonClick() {
        //Toast.makeText(this, "onRightButtonClick!", Toast.LENGTH_SHORT).show()
        monthIndex += 1
        val nextMonthData = loadMonthlyData()
        fillCalendar(nextMonthData)
    }


}
package com.sashakhyzhun.androidbarbershopmanagementprototype.utils

import com.alamkanak.weekview.WeekViewEvent
import com.sashakhyzhun.androidbarbershopmanagementprototype.R
import java.util.*

fun getImage(n: Int): Int {
    return when (n) {
        0 -> R.drawable.long_hair3
        1 -> R.drawable.long_hair4
        2 -> R.drawable.long_hair5
        3 -> R.drawable.long_hair1
        4 -> R.drawable.long_hair2

        5 -> R.drawable.hair_example_1
        6 -> R.drawable.hair_example_2
        7 -> R.drawable.hair_example_3
        8 -> R.drawable.hair_example_4
        9 -> R.drawable.hair_example_5
        10 -> R.drawable.hair_example_6
        11 -> R.drawable.hair_example_7
        12 -> R.drawable.hair_example_8
        13 -> R.drawable.hair_example_9
        14 -> R.drawable.hair_example_10
        15 -> R.drawable.hair_example_11
        16 -> R.drawable.hair_example_12

        else -> R.drawable.long_hair1
    }
}


fun getName(i: Int): String {
    return when (i) {
        0 -> "Robert"
        1 -> "Thomas"
        2 -> "Kevin"
        3 -> "James"
        4 -> "Andrew"
        5 -> "Richard"
        6 -> "William"
        7 -> "John"
        8 -> "Mike"
        9 -> "Michal"
        10 -> "Mark"
        11 -> "Alex"
        12 -> "David"
        13 -> "Bob"
        14 -> "Steven"
        15 -> "David"
        16 -> "Brian"
        else -> "Alex"
    }
}


fun getMockEvents(calendar: Calendar): MutableList<out WeekViewEvent> {
    val weekViewList: MutableList<WeekViewEvent> = mutableListOf()

    var start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 8, 0, 0)

    var end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(1, "Mike", start, end))


    start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 9, 0, 0)

    end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(2, "Michal", start, end))


    start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 10, 0, 0)

    end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(3, "Alex", start, end))


    start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 11, 0, 0)

    end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(4, "David", start, end))

    start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 12, 0, 0)

    end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(5, "William", start, end))


    start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 13, 0, 0)

    end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(6, "Steven", start, end))


    start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 14, 0, 0)

    end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(7, "Brian", start, end))


    start = Calendar.getInstance()
    start.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 15, 0, 0)

    end = Calendar.getInstance()
    end.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), start.get(Calendar.HOUR_OF_DAY), 58, 0)

    weekViewList.add(WeekViewEvent(8, "David", start, end))

    return weekViewList
}


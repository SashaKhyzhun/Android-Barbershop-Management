package com.sashakhyzhun.androidbarbershopmanagementprototype.utils

import com.sashakhyzhun.androidbarbershopmanagementprototype.R

fun getImage(n: Int): Int {
    return when (n) {
        0 -> R.drawable.long_hair3
        1 -> R.drawable.long_hair4
        2 -> R.drawable.long_hair5
        3 -> R.drawable.long_hair1
        4 -> R.drawable.long_hair2

        5 -> R.drawable.long_hair3
        6 -> R.drawable.long_hair4
        7 -> R.drawable.long_hair5
        8 -> R.drawable.long_hair1
        9 -> R.drawable.long_hair2

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
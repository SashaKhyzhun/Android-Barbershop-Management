package com.sashakhyzhun.androidbarbershopmanagementprototype.utils

import android.widget.Toast
import java.util.*


/**
 * @author SashaKhyzhun
 * Created on 9/13/18.
 */
fun getEventTitle(time: Calendar): String {
    return String.format(
            "Event of %02d:%02d %s/%d",
            time.get(Calendar.HOUR_OF_DAY),
            time.get(Calendar.MINUTE),
            time.get(Calendar.MONTH) + 1,
            time.get(Calendar.DAY_OF_MONTH)
    )
}

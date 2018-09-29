package com.sashakhyzhun.androidbarbershopmanagementprototype.model

import java.util.*

data class AcceptedRequest(
        val name: String,
        val regDay: Calendar,
        val startHour: Int,
        val endHour: Int,
        val photo: Int
) : User()
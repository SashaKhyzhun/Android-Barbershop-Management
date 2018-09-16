package com.sashakhyzhun.androidbarbershopmanagementprototype.model

import java.util.*

data class IncomingRequest(val name: String, val regDay: Calendar, val startHour: Int, val endHour: Int)
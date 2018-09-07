package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.barbers

import com.sashakhyzhun.androidbarbershopmanagementprototype.model.Barber

interface BarbersOnClick {

    fun onClicked(barber: Barber, position: Int)

}
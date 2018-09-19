package com.sashakhyzhun.androidbarbershopmanagementprototype.model

import android.os.Parcel
import android.os.Parcelable

/**
 * @author SashaKhyzhun
 * Created on 9/18/18.
 */
data class Hair(var name: String, var resId: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(name)
        p0.writeInt(resId)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Hair> {
        override fun createFromParcel(parcel: Parcel): Hair {
            return Hair(parcel)
        }

        override fun newArray(size: Int): Array<Hair?> {
            return arrayOfNulls(size)
        }
    }
}
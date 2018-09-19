package com.sashakhyzhun.androidbarbershopmanagementprototype.model

import android.os.Parcel
import android.os.Parcelable


class Barber(
        var profileImage: Int,
        var name: String,
        var email: String,
        var phone: String,
        var sex: String,
        var age: String
        //var calendar: Calendar
) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(profileImage)
        p0?.writeString(name)
        p0?.writeString(email)
        p0?.writeString(phone)
        p0?.writeString(sex)
        p0?.writeString(age)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Barber> {
        override fun createFromParcel(parcel: Parcel): Barber {
            return Barber(parcel)
        }

        override fun newArray(size: Int): Array<Barber?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.navigationcomponent.model

import android.os.Parcel
import android.os.Parcelable



data class Money(val amount: Parcel) : Parcelable {

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Money> {
        override fun createFromParcel(parcel: Parcel): Money {
            return Money(parcel)
        }

        override fun newArray(size: Int): Array<Money?> {
            return arrayOfNulls(size)
        }
    }

}



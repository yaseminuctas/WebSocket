package com.yaseminuctas.betbullcase.data.model

import android.os.Parcel
import android.os.Parcelable
import com.yaseminuctas.betbullcase.data.network.Datum
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MockData(): Parcelable {

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    constructor(parcel: Parcel) : this() {
        data = parcel.createTypedArrayList(Datum)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MockData> {
        override fun createFromParcel(parcel: Parcel): MockData {
            return MockData(parcel)
        }

        override fun newArray(size: Int): Array<MockData?> {
            return arrayOfNulls(size)
        }
    }

}
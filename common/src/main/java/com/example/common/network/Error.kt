package com.example.common.network;

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Error() : Parcelable {
    @SerializedName("code")
    var code: String = ""

    @SerializedName("message")
    var message: String = ""

    constructor(code: String, message: String) : this() {
        this.code = code
        this.message = message
    }

    override fun toString(): String {
        return "ErrorVO{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            '}';
    }

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Error> {
        override fun createFromParcel(parcel: Parcel): Error {
            return Error(parcel)
        }

        override fun newArray(size: Int): Array<Error?> {
            return arrayOfNulls(size)
        }
    }
}
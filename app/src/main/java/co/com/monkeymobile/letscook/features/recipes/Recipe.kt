package co.com.monkeymobile.letscook.features.recipes

import android.os.Parcel
import android.os.Parcelable

data class Recipe(val id: Int?, val title: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel) = Recipe(parcel)

        override fun newArray(size: Int): Array<Recipe?> = arrayOfNulls(size)
    }
}
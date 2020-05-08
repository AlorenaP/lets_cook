package co.com.monkeymobile.letscook.features.recipe_detail

import android.os.Parcel
import android.os.Parcelable

class RecipeDetails(
    val id: Int?,
    val title: String?,
    val rating: Int?,
    val image: String?,
    val instructions: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeValue(rating)
        parcel.writeString(image)
        parcel.writeString(instructions)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<RecipeDetails> {
        override fun createFromParcel(parcel: Parcel) = RecipeDetails(parcel)

        override fun newArray(size: Int) = arrayOfNulls<RecipeDetails?>(size)
    }
}
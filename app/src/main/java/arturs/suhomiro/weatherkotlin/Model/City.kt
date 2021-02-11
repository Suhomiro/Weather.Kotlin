package arturs.suhomiro.weatherkotlin.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val city: String,
    val temperature: String,
) : Parcelable
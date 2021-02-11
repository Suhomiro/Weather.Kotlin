package arturs.suhomiro.weatherkotlin.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val wind: Double = 0.0,
    val feelsLike: String = "",
    val barometer: Int = 0
) : Parcelable

fun getDefaultCity() = City("Riga", "-18")

fun getWorldCities(): List<Weather> {
    return listOf(
        Weather(City("London", "+10"), 7.5, "+12", 720),
        Weather(City("Tokyo", "+17"), 3.2, "+15", 721),
        Weather(City("Paris", "+5"), 5.5, "+6", 719),
        Weather(City("Berlin", "+1"), 1.2, "0", 730),
        Weather(City("Rome", "+14"), 0.3, "+17", 710),
        Weather(City("Minsk", "-20"), 3.2, "-23", 728),
        Weather(City("Istambul", "+25"), 10.0, "+23", 715),
        Weather(City("Washington", "+5"), 4.5, "+4", 713),
        Weather(City("Kiev", "-10"), 1.5, "-14", 715),
        Weather(City("Pekin", "+18"), 5.5, "+20", 719)
    )
}

fun getLatvianCities(): List<Weather> {
    return listOf(
        Weather(City("Riga", "-18"), 0.0, "-20", 713),
        Weather(City("Liepaja", "-10"), 3.3, "-12", 720),
        Weather(City("Ventspils", "-13"), 4.5, "-15", 721),
        Weather(City("Daugavpils", "-23"), 7.4, "-25", 718),
        Weather(City("Madona", "-22"), 3.2, "-25", 722),
        Weather(City("Aluksne", "-25"), 2.4, "-27", 723),
        Weather(City("Sigulda", "-20"), 1.1, "-18", 730),
        Weather(City("Valmiera", "-21"), 1.2, "-22", 717),
        Weather(City("Salacgriva", "-17"), 4.5, "-15", 718),
        Weather(City("Saulkrasti", "-16"), 5.9, "-15", 724)
    )
}


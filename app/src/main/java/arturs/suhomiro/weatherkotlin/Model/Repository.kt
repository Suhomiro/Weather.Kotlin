package arturs.suhomiro.weatherkotlin.Model

interface Repository {

        fun getWeatherFromServer(): Weather
        fun getWeatherFromLocalStorageLv(): List<Weather>
        fun getWeatherFromLocalStorageWorld(): List<Weather>

}
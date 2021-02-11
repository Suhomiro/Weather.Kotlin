package arturs.suhomiro.weatherkotlin.Model

class RepositoryImpl: Repository{
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageLv(): List<Weather> {
        return getLatvianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }


}


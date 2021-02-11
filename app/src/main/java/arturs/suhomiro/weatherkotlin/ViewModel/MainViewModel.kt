package arturs.suhomiro.weatherkotlin.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arturs.suhomiro.weatherkotlin.Iteractors.ImageInteractor
import arturs.suhomiro.weatherkotlin.Model.Repository
import arturs.suhomiro.weatherkotlin.Model.RepositoryImpl
import java.lang.Thread.sleep


class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) :
    ViewModel() {
    var fabImageLiveData: MutableLiveData<Int> = MutableLiveData()


    lateinit var imageInteractor: ImageInteractor

    private var isDataSetLv: Boolean = true

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceLv() = getDataFromLocalSource(true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(false)

    fun getWeatherFromRemoteSource() = getDataFromLocalSource( true)

    fun onFABClicked() {
        if (isDataSetLv) {

            getWeatherFromLocalSourceWorld()
            fabImageLiveData.postValue(imageInteractor.worldImage)
        } else {
            getWeatherFromLocalSourceLv()
            fabImageLiveData.postValue(imageInteractor.lvImage)
        }
        isDataSetLv = !isDataSetLv
    }

    private fun getDataFromLocalSource(isLatvia: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(500)
            liveDataToObserve.postValue(AppState.Success(
                if (isLatvia) repository.getWeatherFromLocalStorageLv()
                else repository.getWeatherFromLocalStorageWorld())
            )
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
    }

}


package arturs.suhomiro.weatherkotlin.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import arturs.suhomiro.weatherkotlin.Iteractors.ImageInteractorImpl
import arturs.suhomiro.weatherkotlin.Model.OnItemViewClickListener
import arturs.suhomiro.weatherkotlin.Model.Weather
import arturs.suhomiro.weatherkotlin.R
import arturs.suhomiro.weatherkotlin.ViewModel.AppState
import arturs.suhomiro.weatherkotlin.ViewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(weather: Weather) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, weather)
                manager.beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.imageInteractor = ImageInteractorImpl()

        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.fabImageLiveData.observe(viewLifecycleOwner, { renderFabImage(it) })

        viewModel.getWeatherFromLocalSourceLv()

        mainFragmentFlag.setOnClickListener { viewModel.onFABClicked() }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherData)
            }
            is AppState.Loading -> {
                mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                    .make(mainLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceLv() }
                    .show()
            }
        }
    }

    private fun renderFabImage(imageRes: Int) {
        mainFragmentFlag.setImageResource(imageRes)
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

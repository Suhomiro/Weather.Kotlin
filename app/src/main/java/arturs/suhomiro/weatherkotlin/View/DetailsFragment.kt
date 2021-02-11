package arturs.suhomiro.weatherkotlin.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import arturs.suhomiro.weatherkotlin.Model.Weather
import arturs.suhomiro.weatherkotlin.R
import kotlinx.android.synthetic.main.detail_fragment.*

@Suppress("NAME_SHADOWING")
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        weather?.let { weather ->
            val city = weather.city
            locationCityTextView.text = city.city
            nowDegreeTextView.text = city.temperature
            todayBarometerBarTextView.text = weather.barometer.toString()
            todayWindMsTextView.text = weather.wind.toString()
            todayTermometerTextView.text = weather.feelsLike
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}



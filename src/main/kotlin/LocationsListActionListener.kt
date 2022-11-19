import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zetcode.MainFrame
import kotlinx.coroutines.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.SwingUtilities

class LocationsListActionListener : ActionListener {

    private val frame: MainFrame
    private val locationInJson: String

    constructor(frame: MainFrame, locationInJson: String) {
        this.frame = frame
        this.locationInJson = locationInJson
    }

    override fun actionPerformed(e: ActionEvent) {
        frame.loadingScreen()
        val gson = Gson()
        val point: JsonObject
        val lon: Double
        val lat: Double
        try {
            point = gson.fromJson(locationInJson, JsonObject::class.java).get("point").asJsonObject
            lon = point.get("lng").asDouble
            lat = point.get("lat").asDouble
        } catch (e: Exception) {
            println("Error")
            return
        }
        val def1 = MyHttpClient().getWeatherHTML(lon, lat)
        val def2 = MyHttpClient().getLandmarks(lon, lat, 5000)
        frame.locationInfo(def1.get(), def2.get())

    }
}
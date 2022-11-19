import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zetcode.MainFrame
import kotlinx.coroutines.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.SwingUtilities

class LandmarksListActionListener : ActionListener {
    private val KEY = "0e04701b1054411b374b27b13faeee23"
    private val frame: MainFrame
    private val landmarkJSON: JsonObject

    constructor(frame: MainFrame, locationInJson: JsonObject) {
        this.frame = frame
        this.landmarkJSON = locationInJson
    }

    override fun actionPerformed(e: ActionEvent) {
        SwingUtilities.invokeLater { frame.loadingScreen() }
        val def = MyHttpClient().getLandmarkInfo(landmarkJSON.get("xid").asString)
        def.thenAccept {
            frame.landmarkInfo(it)
        }

    }
}
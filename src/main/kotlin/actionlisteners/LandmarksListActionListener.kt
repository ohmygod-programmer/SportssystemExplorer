package actionlisteners

import MyHttpClient
import com.google.gson.JsonObject
import com.zetcode.MainFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.SwingUtilities

class LandmarksListActionListener : ActionListener {
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
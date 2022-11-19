import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zetcode.MainFrame
import kotlinx.coroutines.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.SwingUtilities

class RestartButtonListActionListener : ActionListener {
    private val KEY = "0e04701b1054411b374b27b13faeee23"
    private val frame: MainFrame

    constructor(frame: MainFrame) {
        this.frame = frame
    }

    override fun actionPerformed(e: ActionEvent) {
        SwingUtilities.invokeLater { frame.startScreen() }
    }
}
package actionlisteners

import MyHttpClient
import com.zetcode.MainFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JTextField
import javax.swing.SwingUtilities

class StartScreenActionListener : ActionListener {
    private val limit: Int
    private val frame: MainFrame
    private val textField: JTextField
    constructor(frame: MainFrame, textField: JTextField, countLimit: Int = 10){
        this.frame = frame
        this.textField = textField
        this.limit = countLimit
    }
    override fun actionPerformed(e: ActionEvent) {
        var name = textField.text
        SwingUtilities.invokeLater { frame.loadingScreen() }
        name = name.replace(' ', '-')
        val def = MyHttpClient().getLocationsList(name, limit)
        def.thenAccept{
                frame.locationsList(it)
        }

    }
}
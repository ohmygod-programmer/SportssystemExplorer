package actionlisteners

import com.zetcode.MainFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.SwingUtilities

class RestartButtonListActionListener : ActionListener {
    private val frame: MainFrame

    constructor(frame: MainFrame) {
        this.frame = frame
    }

    override fun actionPerformed(e: ActionEvent) {
        SwingUtilities.invokeLater { frame.startScreen() }
    }
}
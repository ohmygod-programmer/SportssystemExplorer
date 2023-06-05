import api.ApiController.Companion.getSportsmanTrainers
import forms.DataForm
import forms.SportsmanCard
import model.DataTableModel

fun main(args: Array<String>) {
    val startForm = MainFrame("SportssystemExplorer")
    startForm.startScreen()
    //startForm.toCard(SportsmanCard(1))
    startForm.centred()
    startForm.isVisible = true
}

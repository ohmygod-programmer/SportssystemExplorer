import forms.BuildingCard
import forms.BuildingsSearch
import forms.CompetitionCard
import forms.EntityCard
import forms.Search
import forms.SportsmanCard
import forms.SportsmansSearch
import forms.StartForm
import forms.TrainerCard
import forms.TrainerSearch
import forms.tables.CompetitionSearch
import java.awt.Container
import java.awt.Toolkit
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JTable

class MainFrame(title: String): JFrame(){
    private val stateHistory = ArrayList<Container>()
    private var firstState = true
    init{
        name = title
        defaultCloseOperation = EXIT_ON_CLOSE
    }

    fun centred(){
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)
    }

    fun startScreen(){
        saveState()
        val startForm = StartForm();
        startForm.sportsmansButton.addActionListener({
            toSearch(SportsmansSearch())
        })
        startForm.buildingsButton.addActionListener({
            toSearch(BuildingsSearch())
        })
        startForm.trainersButton.addActionListener({
            toSearch(TrainerSearch())
        })
        startForm.competitionsButton.addActionListener({
            toSearch(CompetitionSearch())
        })
        contentPane = startForm.rootPanel
        refresh()
    }
    fun reset(){
        contentPane.removeAll()
        contentPane.revalidate()
    }
    fun saveState(){
        if (!firstState){
            stateHistory.add(contentPane)
        }
        firstState = false
    }
    fun back(){
        reset()
        if (stateHistory.isNotEmpty() and !firstState){
            contentPane = stateHistory.removeLast()
        }
        else{
            startScreen()
        }
        refresh()
    }

    fun refresh(){
        pack()
        centred()
        revalidate()
    }

    fun toSearch(search: Search){
        saveState()
        search.backButton.addActionListener({
            back()
        })
        contentPane = search.rootPanel
        search.dataTableMouseListener = object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                if (e.clickCount == 2) {
                    val target: JTable = e.getSource() as JTable
                    val row: Int = target.getSelectedRow()
                    val id = search.dataTableModel.getValue(row, "id") as Int
                    if (search::class == SportsmansSearch::class) {
                        toCard(SportsmanCard(id));
                    } else if (search::class == BuildingsSearch::class){
                        toCard(BuildingCard(id))
                    } else if (search::class == TrainerSearch::class){
                        toCard(TrainerCard(id))
                    }else if (search::class == CompetitionSearch::class){
                        toCard(CompetitionCard(id))
                    }

                }
            }
        }
        refresh()

    }
    fun toCard(card: EntityCard){
        saveState()
        card.backButton.addActionListener({
            back()
        })
        contentPane = card.rootPanel
        refresh()
    }

}
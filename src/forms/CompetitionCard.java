package forms;

import api.ApiController;
import com.google.gson.JsonObject;
import model.DataTableModel;
import model.InfoTableModel;

import javax.swing.*;
import java.awt.*;

public class CompetitionCard extends EntityCard{
    public CompetitionCard(Integer id){
        this.getDataTitleLabel().setText("Результаты");
        this.getTitleLabel().setText("Карточка соревнования");
        JTable infoTable = new JTable();
        infoTable.setBackground(new Color(-1));
        infoTable.setForeground(new Color(-16777216));
        infoTable.setGridColor(new Color(-986896));
        infoTable.setShowVerticalLines(false);
        JsonObject competition = ApiController.getCompetition(id);
        infoTable.setModel(new InfoTableModel(competition));
        infoTable.setVisible(true);
        this.getInfoPanel().removeAll();
        this.getInfoPanel().add(infoTable);
        JTable table = new JTable(new DataTableModel(ApiController.getCompetitionResults(id)));
        this.getDataPanel().removeAll();
        this.getDataPanel().add(table);
    }
}

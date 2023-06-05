package forms;

import api.ApiController;
import com.google.gson.JsonObject;
import model.DataTableModel;
import model.InfoTableModel;

import javax.swing.*;
import java.awt.*;

public class BuildingCard extends EntityCard{
    private static String[] COLUMN_NAMES = {"name", "sport", "organizer"};
    public BuildingCard(Integer id){
        this.getDataTitleLabel().setText("Соревнования");
        this.getTitleLabel().setText("Карточка сооружения");
        JTable infoTable = new JTable();
        infoTable.setBackground(new Color(-1));
        infoTable.setForeground(new Color(-16777216));
        infoTable.setGridColor(new Color(-986896));
        infoTable.setShowVerticalLines(false);
        JsonObject sportsman = ApiController.getBuilding(id);
        infoTable.setModel(new InfoTableModel(sportsman));
        infoTable.setVisible(true);
        this.getInfoPanel().removeAll();
        this.getInfoPanel().add(infoTable);
        JTable table = new JTable(new DataTableModel(ApiController.getBuildingCompetitions(id), COLUMN_NAMES));
        this.getDataPanel().removeAll();
        this.getDataPanel().add(table);


    }
}

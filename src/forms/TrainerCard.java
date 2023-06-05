package forms;

import api.ApiController;
import com.google.gson.JsonObject;
import model.DataTableModel;
import model.InfoTableModel;

import javax.swing.*;
import java.awt.*;

public class TrainerCard extends EntityCard{
    private static String[] COLUMN_NAMES = {"firstName", "middleName", "lastName", "dateOfBirth"};
    public TrainerCard(Integer id){
        this.getDataTitleLabel().setText("Подопечные");
        this.getTitleLabel().setText("Карточка тренера");
        JTable infoTable = new JTable();
        infoTable.setBackground(new Color(-1));
        infoTable.setForeground(new Color(-16777216));
        infoTable.setGridColor(new Color(-986896));
        infoTable.setShowVerticalLines(false);
        JsonObject sportsman = ApiController.getTrainer(id);
        infoTable.setModel(new InfoTableModel(sportsman));
        infoTable.setVisible(true);
        this.getInfoPanel().removeAll();
        this.getInfoPanel().add(infoTable);
        JTable table = new JTable(new DataTableModel(ApiController.getTrainerSportsmans(id), COLUMN_NAMES));
        this.getDataPanel().removeAll();
        this.getDataPanel().add(table);
    }
}

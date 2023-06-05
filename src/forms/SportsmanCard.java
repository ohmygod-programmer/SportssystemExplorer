package forms;

import api.ApiController;
import com.google.gson.JsonObject;
import model.DataTableModel;
import model.InfoTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SportsmanCard extends EntityCard{
    private final JButton trainersButton;
    private static String[] COLUMN_NAMES = {"competition", "result", "awarded"};

    public SportsmanCard(int id){
        this.getDataTitleLabel().setText("Участия в соревнованиях");
        this.getTitleLabel().setText("Карточка спортсмена");
        JTable infoTable = new JTable();
        infoTable.setBackground(new Color(-1));
        infoTable.setForeground(new Color(-16777216));
        infoTable.setGridColor(new Color(-986896));
        infoTable.setShowVerticalLines(false);
        JsonObject sportsman = ApiController.getSportsman(id);
        InfoTableModel dataModel = new InfoTableModel(sportsman);
        infoTable.setModel(dataModel);
        JButton okButton = new JButton();
        okButton.setBackground(new Color(-1));
        okButton.setBorderPainted(false);
        okButton.setContentAreaFilled(false);
        okButton.setDefaultCapable(true);
        okButton.setEnabled(true);
        okButton.setFocusPainted(false);
        okButton.setFocusable(true);
        okButton.setForeground(new Color(-1));
        okButton.setIcon(new ImageIcon(getClass().getResource("/buttons/button_save.png")));
        okButton.setInheritsPopupMenu(false);
        okButton.setOpaque(false);
        okButton.setPressedIcon(new ImageIcon(getClass().getResource("/buttons/button_save_pressed.png")));
        okButton.setRequestFocusEnabled(true);
        okButton.setRolloverEnabled(true);
        okButton.setRolloverIcon(new ImageIcon(getClass().getResource("/buttons/button_save_selected.png")));
        okButton.setSelected(false);
        okButton.setVisible(true);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(dataModel.getData().toString());
                JsonObject data = dataModel.getData();
                //ApiController.putSportsman(data);
            }
        });
        this.getOptionsPanel().add(okButton, BorderLayout.WEST);
        infoTable.setVisible(true);
        this.getInfoPanel().removeAll();
        this.getInfoPanel().add(infoTable);

        trainersButton = new JButton();
        trainersButton.setBackground(new Color(-1));
        trainersButton.setBorderPainted(false);
        trainersButton.setContentAreaFilled(false);
        trainersButton.setDefaultCapable(true);
        trainersButton.setEnabled(true);
        trainersButton.setFocusPainted(false);
        trainersButton.setFocusable(true);
        trainersButton.setForeground(new Color(-1));
        trainersButton.setIcon(new ImageIcon(getClass().getResource("/buttons/sportsmanCard/button_small_trenery.png")));
        trainersButton.setInheritsPopupMenu(false);
        trainersButton.setOpaque(false);
        trainersButton.setPressedIcon(new ImageIcon(getClass().getResource("/buttons/sportsmanCard/button_small_trenery_pressed.png")));
        trainersButton.setRequestFocusEnabled(true);
        trainersButton.setRolloverEnabled(true);
        trainersButton.setRolloverIcon(new ImageIcon(getClass().getResource("/buttons/sportsmanCard/button_small_trenery_selected.png")));
        trainersButton.setSelected(false);
        trainersButton.setVisible(true);
        trainersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DataForm dataForm = new DataForm();
                dataForm.setTitleText("Тренеры спортсмена");
                dataForm.getDataTable().setModel(new DataTableModel(ApiController.getSportsmanTrainers(id),new String[]{"first_name", "middle_name", "last_name"}));
            }
        });
        this.getOptionsPanel().add(trainersButton, BorderLayout.EAST);



        JTable table = new JTable(new DataTableModel(ApiController.getSportsmanResults(id),COLUMN_NAMES));
        this.getDataPanel().removeAll();
        this.getDataPanel().add(table);
        revalidate();

    }

    public void setTrainersButtonActionListener(ActionListener actionListener){
        trainersButton.addActionListener(actionListener);
    }
}

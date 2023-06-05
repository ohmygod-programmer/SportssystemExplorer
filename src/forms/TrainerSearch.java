package forms;

import api.ApiController;
import com.google.gson.JsonArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainerSearch extends Search{
    private static int MAXROWSNUM = 30;
    private static String[] COLUMN_NAMES = {"first_name", "middle_name", "last_name"};

    public TrainerSearch(){
        setTitleText("Сооружения");
        getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JsonArray res = ApiController.findTrainers(getSearchText(), MAXROWSNUM);
                if (res.isEmpty()) {
                    showNotFound();
                } else {
                    generateTable(res, COLUMN_NAMES);
                }
            }
        });
        generateTable(ApiController.findTrainers(null, 30), COLUMN_NAMES);

    }
}

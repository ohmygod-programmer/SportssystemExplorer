package forms;

import api.ApiController;
import com.google.gson.JsonArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingsSearch extends Search {
    private static int MAXROWSNUM = 30;
    private static String[] COLUMN_NAMES = {"name", "address"};

    public BuildingsSearch(){
        setTitleText("Сооружения");
        getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JsonArray res = ApiController.findBuildings(getSearchText(), MAXROWSNUM);
                if (res.isEmpty()) {
                    showNotFound();
                } else {
                    generateTable(res, COLUMN_NAMES);
                }
            }
        });
        generateTable(ApiController.findBuildings(null, 30), COLUMN_NAMES);

    }
}

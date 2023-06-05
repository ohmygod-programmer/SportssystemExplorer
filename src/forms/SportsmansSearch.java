package forms;

import api.ApiController;
import com.google.gson.JsonArray;

import java.awt.event.*;
import java.util.ArrayList;

public class SportsmansSearch extends Search{
    private static int MAXROWSNUM = 30;
    private static String[] COLUMN_NAMES = {"first_name", "middle_name", "last_name", "date_of_birth"};

    public SportsmansSearch(){
        setTitleText("Спортсмены");
        getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JsonArray res = ApiController.findSportsmans(getSearchText(), MAXROWSNUM);
                if (res.isEmpty()) {
                    showNotFound();
                } else {
                    generateTable(res, COLUMN_NAMES);
                }
            }
        });
        generateTable(ApiController.findSportsmans(null, 30), COLUMN_NAMES);

    }
}



package forms.tables;

import api.ApiController;
import com.google.gson.JsonArray;
import forms.Search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompetitionSearch extends Search {
    private static int MAXROWSNUM = 30;
    private static String[] COLUMN_NAMES = {"name", "building", "organizer"};
    public CompetitionSearch(){
        setTitleText("Соревнования");
        getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JsonArray res = ApiController.findCompetitions(getSearchText(), MAXROWSNUM);
                if (res.isEmpty()) {
                    showNotFound();
                } else {
                    generateTable(res, COLUMN_NAMES);
                }
            }
        });
        generateTable(ApiController.findCompetitions(null, MAXROWSNUM), COLUMN_NAMES);

    }
}

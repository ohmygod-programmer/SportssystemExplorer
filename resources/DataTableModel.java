import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {

    private JsonArray data = new JsonArray();
    private String[] columnNames = null;

    public DataTableModel(JsonArray data) {
        this.data = data;
        JsonObject obj = data.get(0).getAsJsonObject();
        columnNames = obj.keySet().toArray(new String[0]);
    }

    public DataTableModel(JsonArray data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object userAttribute = null;
        JsonObject object = data.get(row).getAsJsonObject();
        return object.get(columnNames[column]);
    }
}
package model

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import javax.swing.table.AbstractTableModel

class InfoTableModel : AbstractTableModel{

    private val data : JsonObject
    private val fields : Array<String>
    constructor(data: JsonObject) {
        this.data = data
        fields = data.keySet().toTypedArray()
    }
    override fun getColumnCount(): Int {
        return 2
    }

    override fun getRowCount(): Int {
        return data.size()
    }

    override fun getValueAt(row: Int, column: Int): Any {
        if (column == 0){
            return fields[row]
        }
        else{
            try {
                return data.get(fields[row]).asString
            }
            catch (_: Exception){
                return data.get(fields[row])
            }
        }
    }

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        val v = aValue as String
        data.addProperty(fields[rowIndex], v)
        super.setValueAt(aValue, rowIndex, columnIndex)
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        if (columnIndex>0){
            return true
        }
        else{
            return false
        }
    }

    fun getData(): JsonObject {
        return data
    }


}
package model

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import javax.swing.table.AbstractTableModel

class DataTableModel : AbstractTableModel {
    private var data = JsonArray()
    var columnNames: Array<String>? = null

    constructor(data: JsonArray) {
        this.data = data

        val obj = data[0].asJsonObject
        columnNames = obj.keySet().toTypedArray()
    }

    constructor(data: JsonArray, columnNames: Array<String>?) {
        this.data = data
        if (columnNames.isNullOrEmpty()){
            val obj = data[0].asJsonObject
            this.columnNames = obj.keySet().toTypedArray()
        }
        this.columnNames = columnNames
    }

    override fun getColumnName(column: Int): String {
        return columnNames!![column]
    }

    override fun getColumnCount(): Int {
        return columnNames!!.size
    }

    override fun getRowCount(): Int {
        return data.size()
    }

    override fun getValueAt(row: Int, column: Int): Any {
        val obj = data[row].asJsonObject
        try {
            return obj.get(columnNames!![column]).asString
        }
        catch (_ : Exception){
            return obj.get(columnNames!![column])
        }
    }

    fun getValue(row: Int, column: String) : Any{
        val obj = data[row].asJsonObject
        try {
            return obj.get(column).asInt
        }
        catch (_: Exception){}
        try {
            return obj.get(column).asString
        }
        catch (e : Exception){
            return obj.get(column)
        }
    }

    fun getObject(row: Int): Any {
        return data[row].asJsonObject
    }
}
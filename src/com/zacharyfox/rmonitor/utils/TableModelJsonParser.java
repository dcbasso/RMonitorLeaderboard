package com.zacharyfox.rmonitor.utils;

import com.zacharyfox.rmonitor.entities.Competitor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.TableModel;

/**
 * Created by dcbasso on 26/06/17.
 */
public class TableModelJsonParser {

    private static final String BEST_TIME = "Best Time";

    public static JSONObject toJson(TableModel tableModel) throws JSONException {
        JSONObject jsonObject = null;
        if (tableModel != null) {
            jsonObject = new JSONObject();
            JSONArray items = new JSONArray();
            JSONObject jsonRowItem;
            for (int rowIndex =0; rowIndex < tableModel.getRowCount(); rowIndex++) {
                jsonRowItem = new JSONObject();
                for (int columnIndex =0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
                    put(jsonRowItem, tableModel.getColumnName(columnIndex), tableModel.getValueAt(rowIndex, columnIndex));
                }
                items.put(jsonRowItem);
            }
            System.out.println(items.toString());
        }
        return jsonObject;
    }

    private static void put(JSONObject jsonRowItem, String columnName, Object value) throws JSONException {
        if (value.getClass().equals(Integer.class)) {
            Integer newValue = (Integer) value;
            jsonRowItem.put(columnName, newValue);
        } else if (value.getClass().equals(Double.class)) {
            Double newValue = (Double) value;
            jsonRowItem.put(columnName, newValue);
        } else {
            jsonRowItem.put(columnName, value.toString());
        }
        if (BEST_TIME.equals(columnName)) {
            jsonRowItem.put("fastestLap", Competitor.getFastestLap().equals(value));
        }
    }

}

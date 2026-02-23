package com.choucair.mobile.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataUtil {
    public static <T> List<T> setData(DataTable dataTable, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dataList.add(new ObjectMapper().convertValue(map, clazz));
        }
        return dataList;
    }
}

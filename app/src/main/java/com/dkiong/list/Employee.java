package com.dkiong.list;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Employee extends HashMap<String, String> {
    public Employee(String id, String name, String address, String department) {
        put("id", id);
        put("name", name);
        put("address", address);
        put("department", department);
    }

    public static List<Employee> jread() {
        String url = "http://dkiong.no-ip.biz/site/emp";
        List<Employee> list = new ArrayList<Employee>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(url);
        try {
            for (int i =0; i<a.length(); i++) {
                String id = a.getString(i);
                Log.i("Employee", id);
                JSONObject obj = JSONParser.getJSONFromUrl(String.format("%s/%s", url, id));
                list.add(new Employee(obj.getString("eid"), obj.getString("name"),
                        obj.getString("address"), obj.getString("department")));
            }
        } catch (Exception e) {
            Log.e("NewsItem", "JSONArray error");
        }
        return(list);
    }

}

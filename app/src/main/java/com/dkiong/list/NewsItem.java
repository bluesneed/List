package com.dkiong.list;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsItem extends HashMap<String, String> {

    public NewsItem(String area, String title, String url) {
        put("area", area);
        put("title", title);
        put("url", url);
    }

    public static List<NewsItem> read() {
        List<NewsItem> list = new ArrayList<NewsItem>();
        try {
            URI uri = new URI("http://dkiong.no-ip.biz/site/news");
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = new DefaultHttpClient().execute(httpget);
            InputStream inp = response.getEntity().getContent();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inp));
            String area;
            while ((area = rd.readLine()) != null) {
                String title = rd.readLine();
                String url = rd.readLine();
                list.add(new NewsItem(area, title, url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return(list);
    }

    public static List<NewsItem> jread() {
        List<NewsItem> list = new ArrayList<NewsItem>();
        JSONArray a = JSONParser.getJSONArrayFromUrl("http://dkiong.no-ip.biz/site/news?json");
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new NewsItem(b.getString("cat"), b.getString("title"),
                        b.getString("url")));
            }
        } catch (Exception e) {
            Log.e("NewsItem", "JSONArray error");
        }
        return(list);
    }
}
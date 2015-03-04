package com.dkiong.list;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;


public class NewsList extends ActionBarActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean useAsync=true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(this);
        if (useAsync) {
            new AsyncTask<Void, Void, List<NewsItem>>() {
                @Override
                protected List<NewsItem> doInBackground(Void... params) {
                    return NewsItem.jread();
                }
                @Override
                protected void onPostExecute(List<NewsItem> result) {
                    SimpleAdapter adapter = new SimpleAdapter(NewsList.this,
                            result, R.layout.row2,
                            new String[]{"title", "area"},
                            new int[]{R.id.text1, R.id.text2});
                    list.setAdapter(adapter);
                }
            }.execute();
        } else {
            // StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            SimpleAdapter adapter = new SimpleAdapter(NewsList.this,
                    NewsItem.jread(), R.layout.row2,
                    new String[]{"title", "area"},
                    new int[]{R.id.text1, R.id.text2});
            list.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        NewsItem s = (NewsItem) av.getAdapter().getItem(position);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(s.get("url")));
        startActivity(i);
    }

}

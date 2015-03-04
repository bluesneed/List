package com.dkiong.list;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_list);
        final ListView list=(ListView)findViewById(R.id.cards_list);


        new AsyncTask<Void, Void, List<Employee>>() {
            @Override
            protected List<Employee> doInBackground(Void... params) {
                return Employee.jread();
            }
            @Override
            protected void onPostExecute(List<Employee> result) {
                /*
                SimpleAdapter adapter = new SimpleAdapter(EmployeeList.this,
                        result, R.layout.row2,
                        new String[]{"name", "id"},
                        new int[]{R.id.text1, R.id.text2});
                */
                CardAdapter adapter = new CardAdapter(MainActivity.this, R.layout.list_item_card, result);
                list.setAdapter(adapter);
            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri uri;
        Intent i;
        switch (item.getItemId()) {
            case R.id.option1:
                i = new Intent(this, StringList.class);
                startActivity(i);
                return true;
            case R.id.option2:
                i = new Intent(this, StudentList.class);
                startActivity(i);
                return true;
            case R.id.option3:
                i = new Intent(this, NewsList.class);
                startActivity(i);
                return true;
            case R.id.option4:
                i = new Intent(this, EmployeeList.class);
                startActivity(i);
                return true;
            case R.id.option5:
                i=new Intent(this,CardList.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
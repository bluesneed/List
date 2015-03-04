package com.dkiong.list;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class EmployeeList extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(this);
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
                MyAdapter adapter = new MyAdapter(EmployeeList.this, R.layout.row3, result);
                list.setAdapter(adapter);
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        Employee s = (Employee) av.getAdapter().getItem(position);
        Intent intent = new Intent(this, EmployeeDetails.class);
        intent.putExtra("details", s);
        startActivity(intent);
    }
}

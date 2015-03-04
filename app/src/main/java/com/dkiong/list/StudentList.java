package com.dkiong.list;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class StudentList extends ActionBarActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        SimpleAdapter adapter = new SimpleAdapter
                (this, Student.getStudents(), R.layout.row2,
                        new String[]{"name", "age"},
                        new int[]{R.id.text1, R.id.text2});
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        Student s = (Student) av.getAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), s.get("name") + " selected"+id,
                Toast.LENGTH_SHORT).show();
    }

}

package com.dkiong.list;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;


public class EmployeeDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        HashMap<String,String> obj = (HashMap) getIntent().getSerializableExtra("details");
        TextView t1 = (TextView) findViewById(R.id.textView6);
        TextView t2 = (TextView) findViewById(R.id.textView7);
        TextView t3 = (TextView) findViewById(R.id.textView8);
        TextView t4 = (TextView) findViewById(R.id.textView9);
        t1.setText(obj.get("name"));
        t2.setText(obj.get("id"));
        t3.setText(obj.get("address"));
        t4.setText(obj.get("department"));
        final ImageView image = (ImageView) findViewById(R.id.imageView);
        new AsyncTask<String, Void, Bitmap>() {
            Bitmap b;
            @Override
            protected Bitmap doInBackground(String... id) {
                String url=String.format("http://dkiong.no-ip.biz/site/static/photo/%s.jpg",id[0]);
                try {
                    InputStream in = new URL(url).openStream();
                    b = BitmapFactory.decodeStream(in);
                } catch (Exception ex) {
                    Log.e("Bitmap Error", ex.toString());
                }
                return b;
            }
            @Override
            protected void onPostExecute(Bitmap bitmap) {
               image.setImageBitmap(bitmap);
            }
        }.execute(obj.get("id"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

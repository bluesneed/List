package com.dkiong.list;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CardAdapter extends ArrayAdapter<Employee> {


    private List<Employee> items;

    public CardAdapter(Context context, int resource, List<Employee> items) {
        super(context, resource, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item_card, null);
        Employee p = items.get(position);

        if (p != null) {
            TextView name = (TextView) v.findViewById(R.id.list_item_card_text_up);
            name.setText(p.get("name"));
            TextView id = (TextView) v.findViewById(R.id.list_item_card_text_down);
            id.setText(p.get("id"));
            final ImageView image = (ImageView) v.findViewById(R.id.imageView);

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
            }.execute(p.get("id"));

        }
        return v;
    }
}
package com.example.eve.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by eve on 11/29/2015.
 */
public class MyAdapter extends BaseAdapter {
    //Explicit
    private Context objContext;
    private String[] nameStrings,countStrings;

    public MyAdapter(String[] countStrings, Context objContext, String[] nameStrings) {
        this.countStrings = countStrings;
        this.objContext = objContext;
        this.nameStrings = nameStrings;
    }//Constructor

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.list_view_name, viewGroup, false);

        TextView nameTextView = (TextView) objView1.findViewById(R.id.txtReName);
        nameTextView.setText( nameStrings[i]);

        TextView countTextView = (TextView) objView1.findViewById(R.id.txtRecount);
        countTextView.setText("จำนวนครั้งที่เลี้ยง "+countStrings[i]);


        return objView1;
    }



}//main class

package com.example.eve.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by eve on 11/7/2015.
 */
public class NameAdapter extends BaseAdapter {
    //Explicit
    private Context objContext;
    private String[] nameStrings,countStrings;

    public NameAdapter(String[] countStrings, Context objContext, String[] nameStrings) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.name_listview, viewGroup, false);

        //Forname

        TextView nameTextView = (TextView) objView1.findViewById(R.id.txtListName);
        nameTextView.setText("ชื่อจุลินทรีย์ " +nameStrings[i]);

        //For Count

        TextView countTextView = (TextView) objView1.findViewById(R.id.txtListCount);
        countTextView.setText("จำนวนครั้งที่เลี้ยง "+countStrings[i]);


        return objView1;
    }
}//mainclass

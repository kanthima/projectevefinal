package com.example.eve.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 12/3/15 AD.
 */
public class ResultAdapter extends BaseAdapter{

    private Context objContext;
    private String[] resultStrings;

    public ResultAdapter(Context objContext, String[] resultStrings) {
        this.objContext = objContext;
        this.resultStrings = resultStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return resultStrings.length;
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
        View objView1 = objLayoutInflater.inflate(R.layout.result_listview, viewGroup, false);

        TextView resultTextView = (TextView) objView1.findViewById(R.id.txtShowResult);
        resultTextView.setText(resultStrings[i]);

        return objView1;
    }
}   // Main Class

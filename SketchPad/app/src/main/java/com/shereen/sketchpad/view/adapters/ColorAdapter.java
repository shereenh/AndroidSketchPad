package com.shereen.sketchpad.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shereen.sketchpad.R;

import java.util.ArrayList;

/**
 * Created by shereen on 4/4/19
 */

public class ColorAdapter extends BaseAdapter
{
    ArrayList<Integer> colors;
    Context context;
    boolean isPencil;

    public ColorAdapter(Context context, boolean isPencil)
    {
        this.context=context;
        this.isPencil = isPencil;
        colors=new ArrayList<Integer>();
        int retrieve []=context.getResources().getIntArray(R.array.androidcolors);
        for(int re:retrieve)
        {
            colors.add(re);
        }
    }
    @Override
    public int getCount()
    {
        return colors.size();
    }
    @Override
    public Object getItem(int arg0)
    {
        return colors.get(arg0);
    }
    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }
    @Override
    public View getView(int pos, View view, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(isPencil){
            view = inflater.inflate(R.layout.pencil_item, null);
        }else{
            view = inflater.inflate(R.layout.background_item, null);
        }
        view.setBackgroundColor(colors.get(pos));
        return view;
    }

}

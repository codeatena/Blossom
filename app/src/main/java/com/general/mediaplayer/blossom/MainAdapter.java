package com.general.mediaplayer.blossom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by mac on 20/02/2018.
 */


public class MainAdapter extends ArrayAdapter <String>{

    private final Context context;
    private final String[] values;

    public MainAdapterListener listener;

    public MainAdapter(Context context ,String values[]){

        super(context ,-1 ,values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_main, parent, false);
        TextView textView =  rowView.findViewById(R.id.title_text);
        textView.setText(values[position]);

        ToggleButton toggleButton = rowView.findViewById(R.id.toggle_button);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                listener.iconOnClick(position ,b);
            }
        });

        return rowView;
    }

    public void setListener(MainAdapterListener listener){

        this.listener = listener;
    }

    public interface MainAdapterListener {

        void iconOnClick(int position ,boolean check);

    }
}

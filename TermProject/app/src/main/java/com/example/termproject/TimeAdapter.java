package com.example.termproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 박도희 on 2016-12-03.
 */
//한칸의 정보를 받아와서 시작, 정보, 버튼의 클릭 리스너 정의
public class TimeAdapter extends BaseAdapter {

    ArrayList<Timer> timerArrayList;
    LayoutInflater inflater;
    TextView name, idx;
    Button start,stop,del;

    public TimeAdapter(LayoutInflater inflater, ArrayList<Timer> timerArrayList) {
        this.inflater = inflater;
        this.timerArrayList = timerArrayList;
    }

    @Override
    public int getCount() {
        return timerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return timerArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.timer_array_list_view, null);
        }

        Timer tmpTimer = timerArrayList.get(position);



        return convertView;
    }
}

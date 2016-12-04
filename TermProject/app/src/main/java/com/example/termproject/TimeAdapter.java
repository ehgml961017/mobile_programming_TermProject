package com.example.termproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 박도희 on 2016-12-03.
 */
//한칸의 정보를 받아와서 시작, 정보, 버튼의 클릭 리스너 정의
public class TimeAdapter extends BaseAdapter {

    ArrayList<Timer> timerArrayList;
    LayoutInflater inflater;
    TextView name, idx;
    Button start,stop,del;
    DBHelper helper;
    SQLiteDatabase db;
    Chronometer chronometer;
    long stopTime = 0 ;
    boolean isStart = false;

    public TimeAdapter(DBHelper helper, SQLiteDatabase db, ArrayList<Timer> timerArrayList, LayoutInflater inflater) {
        this.helper = helper;
        this.db = db;
        this.timerArrayList = timerArrayList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return timerArrayList.size();
    }

    @Override
    public Object getItem(int idx) {
        return timerArrayList.get(idx);
    }

    @Override
    public long getItemId(int idx) {
        return idx;
    }

    @Override
    public View getView(int idx, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.timer_array_list_view, null);
        }

        this.name = (TextView)convertView.findViewById(R.id.name);
        this.idx = (TextView)convertView.findViewById(R.id.idxView);
        this.start = (Button)convertView.findViewById(R.id.start);
        this.stop = (Button)convertView.findViewById(R.id.stop);
        this.del = (Button)convertView.findViewById(R.id.del);
        this.chronometer = (Chronometer)convertView.findViewById(R.id.chronometer2);



        Timer tmpTimer = timerArrayList.get(idx);

        this.name.setText(tmpTimer.getName());
        this.idx.setText(Integer.toString(idx));
        this.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deleteIdx = Integer.parseInt(TimeAdapter.this.idx.getText().toString());
                int deleteId = timerArrayList.get(deleteIdx).getId();
                db.execSQL("delete from Timer where idx=? ;",new Object[]{deleteId});
                timerArrayList.remove(deleteIdx);
                TimeAdapter.this.notifyDataSetChanged();
            }
        });

        this.start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//                String startTime = df.format(c.getTime());
                if(!isStart) {
                    isStart = true;
                    Calendar c = Calendar.getInstance();
                    String startTime = new SimpleDateFormat("HH:mm:ss").format(c.getTime());
                    chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
                    chronometer.start();
                }
            }
        });

        this.stop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();
                stopTime = 0;
                chronometer.stop();
                Calendar c = Calendar.getInstance();
                String endTime = new SimpleDateFormat("HH:mm:ss").format(c.getTime());
                isStart = false;
            }

        });

        return convertView;
    }
}

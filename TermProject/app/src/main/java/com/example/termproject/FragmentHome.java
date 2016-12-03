package com.example.termproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 박도희 on 2016-11-29.
 */
public class FragmentHome extends Fragment {

    ArrayList<Timer> timerArrayList = new ArrayList<Timer>(); //타이머들을 보관.
    View v;
    EditText name;
    Button add;
    DBHelper helper;
    SQLiteDatabase db;
    TimeAdapter adapter;
    ListView listView;
    Spinner categorySpinner;

    String[] categoryList = {"MOVING", "STUDYING", "RESTING", "EATING"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v=inflater.inflate(R.layout.fragment_home, container, false);

        categorySpinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, categoryList);
        categorySpinner.setAdapter(spinnerAdapter);

        helper = new DBHelper(v.getContext(), "TermProjectDB", null, 1); //db읽어오기. db open
        db = helper.getWritableDatabase(); //db에서 쓸 수 있는 객체 받아오기. db = open(DBHelper)

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.add = (Button) v.findViewById(R.id.add);
        this.name = (EditText) v.findViewById(R.id.editName);
        this.listView = (ListView) v.findViewById(R.id.TimerListView);
        //db초기화
        initializeDB();
        adapter = new TimeAdapter(getLayoutInflater(savedInstanceState), timerArrayList);
        listView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmpName = name.getText().toString();
                if(tmpName.equals("")){
                    Toast.makeText(v.getContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.execSQL("insert into Timer(category, name) values(?, ?);",new Object[]{(int)categorySpinner.getSelectedItemId(), tmpName});    //categorySpinner.getSelectedItemId()
                Cursor rs = db.rawQuery("select idx from Timer order by idx desc limit 1;",null); //timer에 있는 모든값을 idx값으로 내림차순정렬 > id값을 받아올수있음.(맨 첫번째값)
                rs.moveToNext();
                timerArrayList.add(new Timer(0, rs.getInt(0), tmpName));
                adapter.notifyDataSetChanged(); //리스트뷰 새로고침.
            }
        });

        //DB의 값을 timerArrayList에 담고 어뎁터 설정. > DB만들고 나서 구현.

    }

    public void initializeDB()
    {
        timerArrayList = new ArrayList<>();
        Cursor rs = db.rawQuery("select * from Timer;",null);
        while(rs.moveToNext()){
            timerArrayList.add(new Timer(rs.getInt(1), rs.getInt(0), rs.getString(2)));
        }
    }


}

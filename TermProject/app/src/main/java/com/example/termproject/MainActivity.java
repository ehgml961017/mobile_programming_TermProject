package com.example.termproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Edited by 한채연 on 16. 11. 08.
 * Edited by 허준녕 on 16. 11. 14.
 */

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    ActionBar mActionBar;
    ActionBar.Tab tabHome, tabWrite, tabMap, tabGraph;

    Fragment mFragment; //현재 열려있는 fragment에 대한 instance가 담긴곳

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // 커스텀 액션바 구현부 시작
        View mActionBarLayout = getLayoutInflater().inflate(R.layout.actionbar_main,null);
        mActionBar.setCustomView(mActionBarLayout);


        //커스텀 액션바 구현부 종료

        //탭 구현부 시작
        //홈화면탭
        tabHome = mActionBar.newTab();
        tabHome.setText("Home");
        tabHome.setTabListener(this);
        //작성
        tabWrite = mActionBar.newTab();
        tabWrite.setText("Write");
        tabWrite.setTabListener(this);
        //맵에서 보기
        tabMap = mActionBar.newTab();
        tabMap.setText("Map");
        tabMap.setTabListener(this);
        //그래프
        tabGraph = mActionBar.newTab();
        tabGraph.setText("Graph");
        tabGraph.setTabListener(this);
        //최종 등록
        mActionBar.addTab(tabHome);
        mActionBar.addTab(tabWrite);
        mActionBar.addTab(tabMap);
        mActionBar.addTab(tabGraph);
        //탭 구현 종료

    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        int position = tab.getPosition();

        switch (position) {
            case 0:
                mFragment = new FragmentHome();
                break;
            case 1:
                mFragment = new FragmentWrite();
                break;
            case 2:
                mFragment = new FragmentMap();
                break;
            case 3:
                mFragment = new FragmentGraph();
        }
        ft.replace(R.id.fragmentMainLayout,mFragment);
    }
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){
        //
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){
        //
    }
/*
    private boolean openDatabase(){
        //전역 데이터 베이스 초기화 함수
        Globals.getInstance().DB_Helper = new DBHelper(this);
        Globals.getInstance().db = Globals.getInstance().DB_Helper.getWritableDatabase();

        return true;
    }
*/
}
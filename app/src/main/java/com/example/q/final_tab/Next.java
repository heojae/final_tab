package com.example.q.final_tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Next extends AppCompatActivity {


    static int k=0;
    static final ArrayList<String> items = new ArrayList<String>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);

        Intent intent = getIntent();
        String name = intent.getStringExtra("승자");
        int resultInt = intent.getIntExtra("점수", 0);
        String day = intent.getStringExtra("day");


        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items) ;
        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;
        k=k+1;

        int count;
        count = adapter.getCount();

        // 아이템 추가.
        String mes=Integer.toString(k)+"번쨰 결과"+"    "+doYearMonthDay()+"\n"+"승자:"+name+"    "+"점수:"+Integer.toString(resultInt) ;

        items.add(mes);

        // listview 갱신
        adapter.notifyDataSetChanged();


        Button addButton = (Button)findViewById(R.id.add) ;
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),
                        MainActivity.class
                );
                startActivity(intent);
            }
        }) ;


        Button deleteButton = (Button)findViewById(R.id.delete) ;
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int count, checked ;
                count = adapter.getCount() ;

                if (count > 0) {
                    // 현재 선택된 아이템의 position 획득.
                    checked = listview.getCheckedItemPosition();

                    if (checked > -1 && checked < count) {
                        // 아이템 삭제
                        items.remove(checked) ;

                        // listview 선택 초기화.
                        listview.clearChoices();

                        // listview 갱신.
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }) ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String name = intent.getStringExtra("승자");
        int resultInt = intent.getIntExtra("점수", 0);
        String day = intent.getStringExtra("day");

        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }

    public String doYearMonthDay() { SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HH-mm", Locale.KOREA); Date date = new Date(); String currentDate = formatter.format(date); return currentDate; }

}

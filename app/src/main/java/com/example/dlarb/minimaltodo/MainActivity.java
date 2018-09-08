package com.example.dlarb.minimaltodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;
    RecyclerView RV;
    ArrayList<Product> items = new ArrayList<>();
    RecyclerAdapter mAdapter;
    LinearLayoutManager layoutManager;
    GestureDetector gestureDetector;
    ArrayList<String> strings = new ArrayList<>();

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        RV = findViewById(R.id.Recyclerview);

        gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); //아이템이 어떻게 나열될지 선택 vertical or horizental
        RV.setLayoutManager(layoutManager);


        mAdapter = new RecyclerAdapter(strings);
        RV.setAdapter(mAdapter);

        loadData();
        for(int i=0;i<items.size();i++){
            strings.add(items.get(i).getProductTitle());
        }

        mAdapter.notifyDataSetChanged();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adding = new Intent(MainActivity.this, AddMemoAcivity.class);
                adding.putExtra("position",-1);
                startActivity(adding);
                finish();
            }
        });
    }

    public void loadData(){
        Gson gson = new Gson();
        SharedPreferences mprefs = getSharedPreferences("MyObject",MODE_PRIVATE);
        String json = mprefs.getString("MyObject","");
        ArrayList<Product> mitems;
        mitems = gson.fromJson(json, new TypeToken<ArrayList<Product>>(){}.getType());
        if(mitems != null) items.addAll(mitems);
    }
}

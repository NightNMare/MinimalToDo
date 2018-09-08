package com.example.dlarb.minimaltodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DetailMemoAcitivty extends AppCompatActivity {
    TextView edit_TV;
    TextView delete_TV;

    TextView Title;
    TextView section;

    int pos;
    ArrayList<Product> items = new ArrayList<>();

    @Override
    public void onBackPressed() {
        Intent mainact = new Intent(DetailMemoAcitivty.this, MainActivity.class);
        startActivity(mainact);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memo_acitivty);
        edit_TV = (TextView) findViewById(R.id.edit);
        delete_TV = (TextView) findViewById(R.id.delete);
        Title = findViewById(R.id.View_Title);
        section = findViewById(R.id.View_Section);

        Intent i = getIntent();
        pos = i.getExtras().getInt("position");


            loadData();

            Title.setText(items.get(pos).getProductTitle());
            section.setText(items.get(pos).getProductContent());



        edit_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailMemoAcitivty.this, AddMemoAcivity.class);
                intent.putExtra("position", pos);
                startActivity(intent);
                finish();
            }
        });

        delete_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainact = new Intent(DetailMemoAcitivty.this, MainActivity.class);

                items.remove(pos);
                saveData();

                startActivity(mainact);
                finish();
            }
        });

    }

    public void loadData() {
        Gson gson = new Gson();
        SharedPreferences mprefs = getSharedPreferences("MyObject", MODE_PRIVATE);
        String json = mprefs.getString("MyObject", "");
        ArrayList<Product> mitems;
        mitems = gson.fromJson(json, new TypeToken<ArrayList<Product>>() {
        }.getType());
        if (mitems != null) items.addAll(mitems);
    }
    public void saveData(){
        SharedPreferences mprefs = getSharedPreferences("MyObject",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mprefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        mEditor.putString("MyObject",json);
        mEditor.apply();
    }
}

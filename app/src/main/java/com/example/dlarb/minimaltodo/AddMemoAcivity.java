package com.example.dlarb.minimaltodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class AddMemoAcivity extends AppCompatActivity {

    TextView notsave_TV;
    TextView save_TV;
    TextView Title;
    TextView Section;
    ArrayList<Product> items = new ArrayList<>();
    int pos = 0;


    @Override
    public void onBackPressed() {
        Intent mainact = new Intent(AddMemoAcivity.this, MainActivity.class);
        startActivity(mainact);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo_acivity);
        notsave_TV = (TextView) findViewById(R.id.notsave);
        save_TV = (TextView) findViewById(R.id.save);
        Title = findViewById(R.id.Edit_Title);
        Section = findViewById(R.id.Edit_Section);

        Intent i = getIntent();
        pos = i.getExtras().getInt("position");

        if (pos != -1) {
            loadData();

            Title.setText(items.get(pos).getProductTitle());
            Section.setText(items.get(pos).getProductContent());
        }

        notsave_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainact = new Intent(AddMemoAcivity.this, MainActivity.class);
                startActivity(mainact);
                finish();
            }
        });

        save_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPrefs = getSharedPreferences("MyObject", MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mPrefs.edit();

                if (pos == -1) {
                    loadData();

                    Product MyObject = new Product(Title.getText().toString(), Section.getText().toString());
                    items.add(MyObject);

                    saveData();
                }
                else {


                    Product MyObject = new Product(Title.getText().toString(), Section.getText().toString());
                    items.set(pos,MyObject);

                    saveData();

                }
                Intent mainact = new Intent(AddMemoAcivity.this, MainActivity.class);
                startActivity(mainact);
                finish();
            }
        });
    }

    public void saveData() {
        SharedPreferences mprefs = getSharedPreferences("MyObject", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mprefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        mEditor.putString("MyObject", json);
        mEditor.apply();
    }

    public void loadData() {
        Gson gson = new Gson();
        SharedPreferences mprefs = getSharedPreferences("MyObject", MODE_PRIVATE);
        String json = mprefs.getString("MyObject", "");
        ArrayList<Product> mitems;
        mitems = gson.fromJson(json, new TypeToken<ArrayList<Product>>() {}.getType());
        if (mitems != null) items.addAll(mitems);
    }
}

package com.example.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class SearchWord extends AppCompatActivity {
    private EditText etSearch;
    private Button btnClick;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);

        etSearch = findViewById(R.id.etSearch);
        btnClick = findViewById(R.id.btnClick);
        lstView = findViewById(R.id.lstWord);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick();
            }
        });

    }

    private void btnClick() {
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = myHelper.GetWordByName(etSearch.getText().toString(),sqLiteDatabase);

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 0; i < wordList.size(); i++) {

            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }
        ArrayAdapter<String> StringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())

        );
        lstView.setAdapter(StringArrayAdapter);

    }

}





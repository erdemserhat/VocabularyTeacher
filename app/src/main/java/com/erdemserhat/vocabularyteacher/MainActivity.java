package com.erdemserhat.vocabularyteacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.erdemserhat.vocabularyteacher.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList <Vocabulary> vocabularyArrayList = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    try{

        SQLiteDatabase database = this.openOrCreateDatabase("Vocabularies",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS vocabularies (name VARCHAR, meaning VARCHAR, example VARCHAR)");

        Cursor cursor = database.rawQuery("SELECT * FROM vocabularies",null);

        int nameIx = cursor.getColumnIndex("name");
        int meaningIx = cursor.getColumnIndex("meaning");
        int exampleIx = cursor.getColumnIndex("example");

        while(cursor.moveToNext()){
            String name = cursor.getString(nameIx);
            String meaning = cursor.getString(meaningIx);
            String example = cursor.getString(exampleIx);
            Vocabulary vocabulary = new Vocabulary(name, meaning, example);
            vocabularyArrayList.add(vocabulary);
        }
        cursor.close();



    }catch (Exception e){




    }

    binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    VocabularyAdapter vocabularyAdapter = new VocabularyAdapter(vocabularyArrayList);
    binding.recyclerView.setAdapter(vocabularyAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_vocabulary:
                //Codes will be executed when "Add Vocabulary" button is clicked.
                Toast.makeText(MainActivity.this,"Add button is clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AddVocabularyActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.take_test:
                //Codes will be executed when "Take a Test" button is clicked.
                Toast.makeText(MainActivity.this,"Take a Test button is clicked", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }



}
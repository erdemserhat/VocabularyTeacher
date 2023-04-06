package com.erdemserhat.vocabularyteacher;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.erdemserhat.vocabularyteacher.databinding.ActivityVocabularyShowerBinding;

public class VocabularyShower extends AppCompatActivity {
    private SQLiteDatabase database;
    private ActivityVocabularyShowerBinding binding;
    private Vocabulary vocabulary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVocabularyShowerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent =getIntent();
        vocabulary = (Vocabulary) intent.getSerializableExtra("vocabulary");
        binding.vocabularynameShower.setText(vocabulary.getName());
        binding.vocabularyMeaningShower.setText(vocabulary.getMeaning());
        binding.vocabularyExampleShower.setText(vocabulary.getExampleSentence());
        database =this.openOrCreateDatabase("Vocabularies",MODE_PRIVATE,null);
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String name = vocabulary.getName();
        switch (item.getItemId()){

            case R.id.delete:

                database.execSQL("DELETE FROM vocabularies WHERE name='"+name+"'");
                Toast.makeText(this,name +" was deleted.",Toast.LENGTH_LONG).show();
                Intent intent = intent = new Intent(VocabularyShower.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;


            case R.id.edit:

                Intent intent1 = new Intent (VocabularyShower.this,AddVocabularyActivity.class);
                intent1.putExtra("deletedVocabulary",vocabulary);
                startActivity(intent1);
                finish();
                break;



            default:

        }




        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent (VocabularyShower.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();;
    }
}
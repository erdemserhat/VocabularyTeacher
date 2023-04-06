package com.erdemserhat.vocabularyteacher;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.erdemserhat.vocabularyteacher.databinding.ActivityAddVocabularyBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddVocabularyActivity extends AppCompatActivity {
    int SELECT_PICTURE = 200;
    Vocabulary editVocabulary;
    int flag =0;
    ActivityAddVocabularyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVocabularyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imageChooser();
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = AddVocabularyActivity.this.openOrCreateDatabase("Vocabularies",MODE_PRIVATE,null);
                String vocabularyName = String.valueOf(binding.vocabularyName.getText());
                String vocabularyMeaning = String.valueOf(binding.vocabularyMeaning.getText());
                String vocabularyExample = String.valueOf(binding.vocabularyExampleSentence.getText());

                if(flag>0){
                    database.execSQL("DELETE FROM vocabularies WHERE name ='"+editVocabulary.getName()+"'");
                    database.execSQL("INSERT INTO vocabularies (name, meaning, example) VALUES ('"+vocabularyName+"', '"+vocabularyMeaning+"','"+vocabularyExample+"')");
                    Intent intent = new Intent(AddVocabularyActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    database.execSQL("INSERT INTO vocabularies (name, meaning, example) VALUES ('" + vocabularyName + "', '" + vocabularyMeaning + "', '" + vocabularyExample + "')");
                    Intent intent = new Intent(AddVocabularyActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

        Intent intent = getIntent();
        editVocabulary =(Vocabulary) intent.getSerializableExtra("deletedVocabulary");
        if(editVocabulary!=null){
            flag++;
            String name = editVocabulary.getName();
            String meaning = editVocabulary.getMeaning();
            String example = editVocabulary.getExampleSentence();

            binding.vocabularyName.setText(name);
            binding.vocabularyMeaning.setText(meaning);
            binding.vocabularyExampleSentence.setText(example);



        }




    }

    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        binding.vocabularyImage.setImageBitmap(
                                selectedImageBitmap);
                    }
                }
            });



    @Override
    public void onBackPressed() {
        Intent intent = new Intent (AddVocabularyActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }
}



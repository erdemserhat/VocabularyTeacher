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

    // Setting binding connection between views and ids.
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning the connection.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Database Setup.
    try{
        //Creating a database.
        SQLiteDatabase database = this.openOrCreateDatabase("Vocabularies",MODE_PRIVATE,null);

        //Creating a table and columns with data structure type.
        database.execSQL("CREATE TABLE IF NOT EXISTS vocabularies (name VARCHAR, meaning VARCHAR, example VARCHAR)");

        //define a cursor to read datas.
        Cursor cursor = database.rawQuery("SELECT * FROM vocabularies",null);

        //Defining column indexes.
        int nameIx = cursor.getColumnIndex("name");
        int meaningIx = cursor.getColumnIndex("meaning");
        int exampleIx = cursor.getColumnIndex("example");

        //Adding the data from database to data list which is in the stack ram memory. (Making data accessible from database)
        while(cursor.moveToNext()){
            String name = cursor.getString(nameIx);
            String meaning = cursor.getString(meaningIx);
            String example = cursor.getString(exampleIx);
            Vocabulary vocabulary = new Vocabulary(name, meaning, example);
            vocabularyArrayList.add(vocabulary);
        }
        //Closing cursor.
        cursor.close();



    }catch (Exception e){
        e.printStackTrace();
        System.err.println("An error occurred when the database was initialized.");

        //Parameter is "1" because, program will be finished with error.
        System.exit(1);
 }

    //Setting recyclerview.
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

    //Recyclerview adapter which was already defined is being gave as parameter to our recyclerview
    VocabularyAdapter vocabularyAdapter = new VocabularyAdapter(vocabularyArrayList);
    binding.recyclerView.setAdapter(vocabularyAdapter);



    }

    /**
     * Creating a overflow menu...
     * Overflow items are items of the overflow menu available in the ToolBar in the Android application.
     * The elements present in the overflow menu can be accessed by clicking the three dots in the top of toolbar.
     *
     * Right-click on the res folder, go to New, and click on Android Resource Directory. Select resource type as menu and click on OK.
     * Your menu.xml file structure must be like that ;
     *
     * <menu xmlns:android="http://schemas.android.com/apk/res/android"
     *     xmlns:app="http://schemas.android.com/apk/res-auto">
     *
     *     <item
     *         android:id="@+id/option1"
     *         android:orderInCategory="1"
     *         android:title="Bluetooth"
     *         android:icon="@drawable/ic_bluetooth"
     *         app:showAsAction="ifRoom"/>
     *
     *     <item
     *         android:id="@+id/option2"
     *         android:orderInCategory="2"
     *         android:title="Chat"
     *         android:icon="@drawable/ic_chat"
     *         app:showAsAction="ifRoom"/>
     *
     * </menu>
     *
     * After the defining xml file you can @Override the onCreateOptionMenu method when you do that, you can provide a connection
     * between menu and xml file ;
     *
     * MenuInflater inflater = getMenuInflater();
     * inflater.inflate(R.menu.example_menu, menu);
     * //Menu resource directory.
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    /**
     * If you want to define a particular action (setOnClickListener when your overflow menu items are clicked by user,
     * You should @Override onOptionItemSelected method,if your one of items is clicked, this methot will be called.
     * you can use swicht case and if/else-if by using item id. Don't forget that item which is a instance of MenuItem
     * comes as parameter is your selected/clicked item.
     *
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_vocabulary:
                //Codes will be executed when "Add Vocabulary" button is clicked.
                //Toast.makeText(MainActivity.this,"Add button is clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AddVocabularyActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.take_test:
                //Codes will be executed when "Take a Test" button is clicked.
                //Toast.makeText(MainActivity.this,"Take a Test button is clicked", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }



}
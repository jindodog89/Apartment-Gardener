package com.example.choi.apartmentgardener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LandOwnerAddPost extends AppCompatActivity implements View.OnClickListener {

    private DatabaseManager1 dbmanager;
    private DatabaseManager dbmanagerNew;
    public static User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dbmanager = new DatabaseManager1(this);
        dbmanagerNew = new DatabaseManager(this);
        curUser = MainActivity.curUser;
        Log.w( "LandOwnerAddPost", "onCreate" );
        setContentView(R.layout.activity_land_owner_add_post);
        Button cancel_Button = (Button) findViewById(R.id.buttonCancel_addPost);
        Button save_Button = (Button) findViewById(R.id.buttonSave_addPost);
        cancel_Button.setOnClickListener(this);
        save_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.w( "LandOwnerAddPost", "onClick" );
        switch (v.getId()){
            case R.id.buttonCancel_addPost:Log.w( "MainActivity", "Add selected" );
                goBackToBoard(v);
                break;

            case R.id.buttonSave_addPost:
                Toast.makeText(this, "Post successfully added!", Toast.LENGTH_SHORT).show( );
                //make a function to save object.
                insert(v);
                goBackToBoard(v);
                break;
        }
    }

    public void insert(View v) {
        Log.w( "LandOwnerAddPost", "insert" );
        EditText addTitle = (EditText)findViewById(R.id.add_Title);
        EditText addDescription = (EditText)findViewById(R.id.add_Description);
        EditText addLocation = (EditText)findViewById(R.id.add_Location);
        //LandOwnerBoardActivity.landOwnerList.add(new LandOwnerInfo(0,addTitle.getText().toString(), addDescription.getText().toString(), addLocation.getText().toString()));
        LandOwnerInfo LandOwnewInformation = new LandOwnerInfo( 0,addTitle.getText().toString(), addDescription.getText().toString(), addLocation.getText().toString() );
        //dbmanager.insert(LandOwnewInformation);
        Listing listsingObject = new Listing(curUser.getUsername(),addLocation.getText().toString(), addTitle.getText().toString(), addDescription.getText().toString(), 0);
        dbmanagerNew.insertListing(listsingObject);

        addTitle.setText("");
        addDescription.setText("");
        addLocation.setText("");
    }

    public void goBackToBoard(View v) {
        Intent landOwnerBoardIntent = new Intent(this,LandOwnerBoardActivity.class);
        this.startActivity(landOwnerBoardIntent);
    }
}

package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by diaza6 on 11/14/17.
 * The user can view the details of the chosen garden and
 * view a map view to see the location.
 */

public class GardenActivity extends AppCompatActivity {

    Listing chosenListing = ListingActivity.chosenListing;
    User curUser = MainActivity.curUser;
    //Default constructor
    public GardenActivity(){

    }

    //Display the selected gardens details
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
        TextView textView = (TextView)findViewById(R.id.descriptView);
        String title = chosenListing.getTitle();
        String user = chosenListing.getUser();
        String location = chosenListing.getLocation();
        String description = chosenListing.getDescription();
        int id = chosenListing.getId();
        textView.setTextSize(20.0f);
        textView.setTextColor(getResources().getColor(R.color.colorBlack));
        textView.setText("Title: " + title + "\n\n"
                + "Description: " + description + "\n\n"
                + "Location: " + location);
    }

    //Open the email screen to contact the land owner
    public void contactOwner(View v) {
        //This button will open the email window
        if(curUser != null) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");

            intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(Intent.EXTRA_TEXT, "I'm interested in your Garden Space.");

            startActivity(Intent.createChooser(intent, "Send email"));
        }else{
            String toastInfo = "Please log in";
            Toast.makeText(GardenActivity.this, toastInfo, Toast.LENGTH_LONG).show();
        }
    }
}
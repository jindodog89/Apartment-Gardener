package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by diaza6 on 11/14/17.
 * The listings of the available gardens is taken care of here.
 * The user can view a selected item, go back to a previous screen,
 * or exit the app.
 */

public class ListingActivity  extends AppCompatActivity {
    private DatabaseManager dbManager;
    //public ArrayList<Listing> listings= new ArrayList<>();
    public static Listing chosenListing;
    ScrollView scrollView;
    private int buttonWidth;
    int id1 = 0;
    int id2 = 1;
    String title1 = "Downtown Garden";
    String name1 = "Arturo";
    String loc1 = "Downtown";
    String desc1 = "Lots of room to grow veggies or flowers.";
    Listing initListing1 = new Listing( name1, loc1, title1, desc1,id1);
    String title2 = "North of Belligham Garden";
    String name2 = "Frank";
    String loc2 = "NorthSide";
    String desc2 = "Back yard with great soil.";
    Listing initListing2 = new Listing(name2, loc2, title2, desc2,id2);
    ListingButton listingButton;



//    //Default constructor
//    public ListingActivity(){
//
//    }

    //Display the activity listing screen
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        dbManager = new DatabaseManager(this);
        ArrayList<Listing> listings = dbManager.selectAllListings();
        if(listings.size()== 0){
            dbManager.insertListing(initListing1);
            dbManager.insertListing(initListing2);
        }
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x;
        updateView();


    }

    public void updateView() {
        ArrayList<Listing> listings = dbManager.selectAllListings();
        if(listings.size() > 0){
            scrollView.removeAllViewsInLayout();

            //set up the grid layout
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((listings.size()));// + 1 ) /2);
            grid.setColumnCount(1);


            //create array of buttons, 1 per row
            ListingButton[] buttons = new ListingButton[listings.size()];
            ButtonHandler bh = new ButtonHandler();

            //fill the grid
            int i = 0;
            for(Listing listing: listings) {
                //create the buttons
                buttons[i] = new ListingButton(this, listing);
                buttons[i].setText(buttons[i].getTitle());
                buttons[i]. setId(listing.getId());
                buttons[i].setBackgroundColor(getResources().getColor(R.color.button));
                buttons[i].setTextColor(getResources().getColor(R.color.buttonText));

                //set up event handling
                buttons[i].setOnClickListener(bh);

                //add the button to grid
                grid.addView(buttons[i], buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {

            int listingID = v.getId();
            chosenListing = dbManager.selectListingById(listingID);
//            String toastInfo = " Listings ";
//            Toast.makeText(ListingActivity.this, toastInfo, Toast.LENGTH_LONG).show();
            viewListing(v);

        }
    }

    public void viewListing(View v ){
        Intent listingIntent = new Intent(this, GardenActivity.class);
        this.startActivity(listingIntent);
    }





}
package com.example.choi.apartmentgardener;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class LandOwnerBoardActivity extends AppCompatActivity {

    private User curUser;
    //public static ArrayList<LandOwnerInfo> landOwnerList = new ArrayList<LandOwnerInfo>();
    //private DatabaseManager1 dbManager;
    private DatabaseManager dbManagerNew;
    private ScrollView scrollView;
    private int buttonWidth;
    private String title;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curUser = MainActivity.curUser;
        setContentView(R.layout.activity_land_owner_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //dbManager = new DatabaseManager1(this);
        dbManagerNew = new DatabaseManager(this);
//        if(landOwnerList.size() >0 ){
//            Log.w( "LandOwnerBoardActivity", "onCreate: " + landOwnerList.get(0).getTitle());
//        }

        scrollView = ( ScrollView ) findViewById( R.id.scrollView );
        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        buttonWidth = size.x/2;
        updateView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_land_owner_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch ( id ) {
            case R.id.action_adding:
                Log.w( "LandOwnerBoardActivity", "action_adding" );
                Intent insertIntent = new Intent(LandOwnerBoardActivity.this, LandOwnerAddPost.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_view_listings:
                Intent listingIntent = new Intent(this,ListingActivity.class);
                this.startActivity(listingIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateView() {
        ArrayList<Listing> landOwnerList = dbManagerNew.selectListingsByUser(curUser);
        //ArrayList<Listing> landOwnerList = dbManagerNew.selectAllListings();
        if(landOwnerList.size() > 0) {
            scrollView.removeAllViewsInLayout();
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( ( landOwnerList.size( ) + 1 ) / 2 );
            grid.setColumnCount( 2 );
            TaskButton [] buttons = new TaskButton[landOwnerList.size( )];
            //LandOwnerInfo buttons1 = new LandOwnerInfo[landOwnerList.size( )];

            ButtonHandler bh = new ButtonHandler( );
            //ButtonLongHandler blh = new ButtonLongHandler( );
            int i = 0;
            for(Listing eachInfo : landOwnerList) {
                buttons[i] = new TaskButton( this, eachInfo );
                buttons[i].setText( eachInfo.getTitle( ) + "\n" + eachInfo.getDescription( ) );
                //grid.addView( buttons[i], buttonWidth,GridLayout.LayoutParams.WRAP_CONTENT );
                buttons[i].setOnClickListener( bh );
                grid.addView( buttons[i], buttonWidth,GridLayout.LayoutParams.WRAP_CONTENT );
                i++;
            }
            scrollView.addView( grid );
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // retrieve price of the candy and add it to total
            title = ( ( TaskButton ) v ).getTitle( );
            description = ( ( TaskButton ) v ).getDescription( );
            //String emailAndDueDate = "Email Address: " + emailAddress + "\n" + "Due date: "+dueDate;

            //Toast.makeText( MainActivity.this, emailAndDueDate, Toast.LENGTH_LONG ).show( );
        }
    }

}

package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

/**
 * Created by Art on 11/29/2017.
 */

public class ListingButton extends AppCompatButton {

    private Listing listing;

    public  ListingButton(Context context, Listing newListing){
        super(context);
        listing = newListing;
    }

    public String getTitle(){
        return listing.getTitle();
    }
}
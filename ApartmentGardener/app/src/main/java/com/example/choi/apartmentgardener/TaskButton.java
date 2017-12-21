package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

//display task name in each button
//on a button click, display email address and due date
public class TaskButton extends AppCompatButton {
    private Listing landOwnerTask;
    public TaskButton(Context context, Listing newTask) {
        super( context );
        landOwnerTask = newTask;

    }

    public String getTitle() {return landOwnerTask.getTitle(); }

    public String getDescription( ) {
        return landOwnerTask.getDescription();
    }

    public String getLocation( ) {
        return landOwnerTask.getLocation();
    }

//    public String getDueDate( ) {
//        return task.getDuedate();
//    }
//
//    public String getTaskInformation( ) {
//        return task.getTaskInformation();
//    }
}

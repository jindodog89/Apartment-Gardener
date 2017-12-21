package com.example.choi.apartmentgardener;

/*import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static User curUser;
    private DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button register_Button = (Button) findViewById(R.id.registerButton);
        Log.w("MainActivity","onCreate()");

        //curUser = new User("shameem","landowner","shameem@wwu.edu");

        register_Button.setOnClickListener(this);

    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                Log.w("MainActivity","onClick()");
                register();
                break;
        }
    }*/

   /* public void register(){
        Log.w("MainActivity","register()");
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        this.startActivity(registerIntent);
    }

    public void login(View v){
        EditText usernameET = (EditText) findViewById(R.id.nameET);
        EditText pwdET = (EditText) findViewById(R.id.pwdET);
        String username = usernameET.getText().toString();
        String pwd = pwdET.getText().toString();
        if(dbManager.checkPassword(username, pwd)){
            curUser = dbManager.getUser(username);
            if(curUser.isGardener()){
                //Intent gardenerIntent = new Intent(this, ListingActivity.class);
                //this.startActivity(gardenerIntent);
            }
            else{
                Intent landownerIntent = new Intent(this, LandOwnerBoardActivity.class);
                this.startActivity(landownerIntent);
            }
        }
        else{
            Toast.makeText(this,"Wrong username or password", Toast.LENGTH_LONG).show();
        }
    }

    public void continueAsGuest(View v){
    }
}*/
   //package wwu.edu.apartmentgardener_11_16_17;

           import android.content.Intent;
           import android.support.v7.app.AppCompatActivity;
           import android.os.Bundle;
           import android.util.Log;
           import android.view.View;
           import android.widget.EditText;
           import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    public static User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager(this);
    }

    public void register(View v){
        Log.w("MainActivity","register()111");
        Intent myIntent = new Intent(this, RegisterActivity.class);
        this.startActivity(myIntent);
    }

    public void login(View v){
        EditText usernameET = (EditText) findViewById(R.id.nameET);
        EditText pwdET = (EditText) findViewById(R.id.pwdET);
        String username = usernameET.getText().toString();
        String pwd = pwdET.getText().toString();
        if(dbManager.checkPassword(username, pwd)){
            curUser = dbManager.getUser(username);
            if(curUser.isGardener()){
                Intent gardenerIntent = new Intent(this, ListingActivity.class);
                this.startActivity(gardenerIntent);
            }
            else{
                Intent landownerIntent = new Intent(this, LandOwnerBoardActivity.class);
                this.startActivity(landownerIntent);
            }
        }
        else{
            Toast.makeText(this,"Wrong username or password", Toast.LENGTH_LONG).show();
        }
    }

    public void continueAsGuest(View v){
        curUser = null;
        Intent guestIntent = new Intent(this, ListingActivity.class);
        this.startActivity(guestIntent);
    }
}
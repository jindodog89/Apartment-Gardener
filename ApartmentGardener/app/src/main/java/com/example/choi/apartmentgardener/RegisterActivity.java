package com.example.choi.apartmentgardener;

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.Toast;

/*public class RegisterActivity extends AppCompatActivity{

    private static final String EMAIL_REGEX = "(.+)@(.+)\\.(.+)";
    private static RadioButton gardener;
    private static RadioButton homeowner;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.w("RegisterActivity","onCreate()");
        setContentView(R.layout.activity_register);
        //done();
        //Button register_Button = (Button) findViewById(R.id.signup);
        //register_Button.setOnClickListener(this);
    }

   /* @Override
    public void onClick(View v) {
        Log.w("RegisterActivity","onClick()");
        switch (v.getId()){
            case R.id.signup:
                if(gardener.isChecked()){

                } else if(homeowner.isChecked()){
                    Log.w("RegisterActivity","onClick()-1");
                    Intent landOwnerBoardIntent = new Intent(this,LandOwnerBoardActivity.class);
                    Log.w("RegisterActivity","onClick()-2");
                    this.startActivity(landOwnerBoardIntent);
                    break;
                }
        }
    }*/

 /*   private void done() {
        Log.w("RegisterActivity","done()");
        EditText usernameET = (EditText) findViewById(R.id.usernameET);
        EditText pwdET1 = (EditText) findViewById(R.id.pwdET);
        EditText pwdET2 = (EditText) findViewById(R.id.reenterPwdET);
        EditText emailET = (EditText) findViewById(R.id.emailET);
        gardener = (RadioButton) findViewById(R.id.aptGardener);
        homeowner = (RadioButton) findViewById(R.id.homeowner);
        String username = usernameET.getText().toString();
        String pwd1 = pwdET1.getText().toString();
        String pwd2 = pwdET2.getText().toString();
        String email = emailET.getText().toString();


//        if(pwd1.length() < 6){
//            Toast.makeText(this,"Password must be at least 6 characters", Toast.LENGTH_LONG).show();
//        }
//        else if(!pwd1.equals(pwd2)){
//            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_LONG).show();
//        }
//        else if (!email.matches(EMAIL_REGEX)){
//            Toast.makeText(this,"Not a valid e-mail", Toast.LENGTH_LONG).show();
//        }
//        else if((!gardener.isChecked()) && (!homeowner.isChecked())){
//            Toast.makeText(this,"Must choose account type", Toast.LENGTH_LONG).show();
//        }
//        else{
//            //Add to database
//            this.finish();
//        }

        if(gardener.isChecked()){
            Log.w("RegisterActivity","gardener.isChecked()");
        } else if(homeowner.isChecked()){
            Log.w("RegisterActivity","homeowner.isChecked()");
        }


        Log.w("RegisterActivity","done()-End of done()");
        this.finish();
    }
}*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private static final String EMAIL_REGEX = "(.+)@(.+)\\.(.+)";
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbManager = new DatabaseManager(this);
    }

    public void done(View v){
        EditText usernameET = (EditText) findViewById(R.id.usernameET);
        EditText pwdET1 = (EditText) findViewById(R.id.pwdET);
        EditText pwdET2 = (EditText) findViewById(R.id.reenterPwdET);
        EditText emailET = (EditText) findViewById(R.id.emailET);
        RadioButton gardener = (RadioButton) findViewById(R.id.aptGardener);
        RadioButton homeowner = (RadioButton) findViewById(R.id.homeowner);
        String username = usernameET.getText().toString();
        String pwd1 = pwdET1.getText().toString();
        String pwd2 = pwdET2.getText().toString();
        String email = emailET.getText().toString();


        if(username.length() == 0){
            Toast.makeText(this,"Must enter a username", Toast.LENGTH_LONG).show();
        }
        else if(dbManager.checkUsername(username)){
            Toast.makeText(this,"Username is already taken", Toast.LENGTH_LONG).show();
        }
        else if(pwd1.length() < 6){
            Toast.makeText(this,"Password must be at least 6 characters", Toast.LENGTH_LONG).show();
        }
        else if(!pwd1.equals(pwd2)){
            Toast.makeText(this, "The two passwords don't match", Toast.LENGTH_LONG).show();
        }
        else if (!email.matches(EMAIL_REGEX)){
            Toast.makeText(this,"Not a valid e-mail", Toast.LENGTH_LONG).show();
        }
        else if (dbManager.checkEmail(email)){
            Toast.makeText(this,"E-mail is already being used by another user", Toast.LENGTH_LONG).show();
        }
        else if((!gardener.isChecked()) && (!homeowner.isChecked())){
            Toast.makeText(this,"Must choose account type", Toast.LENGTH_LONG).show();
        }
        else{
            String act_type;
            if(gardener.isChecked())
                act_type = "gardener";
            else
                act_type = "landowner";
            User newUser = new User(username, act_type, email);
            dbManager.insertUser(newUser, pwd1);
            this.finish();
        }
    }
}

package com.example.kirmi.ks1807;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.RadioButton;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import java.util.ArrayList;

public class EditUserDetails extends AppCompatActivity
{
    private final Context context = this;
    final CommonFunctions Common = new CommonFunctions();
    private DatabaseFunctions UserFunctions;
    String UserID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);
        UserFunctions = new DatabaseFunctions(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                     @Override
                     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                         Fragment selectedFragment = null;
                         switch (item.getItemId()) {
                             case R.id.btn_Home:
                                 selectedFragment = BottomNavigationOptions.newInstance();
                                 break;
                         }
                         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                         transaction.replace(R.id.frame_layout, selectedFragment);
                         transaction.commit();
                         return true;
                     }
                 });

        //Display fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, BottomNavigationOptions.newInstance());
        transaction.commit();

        //Get the UserID for this login session.
        Intent intent = getIntent();
        UserID = intent.getStringExtra("UserID");

        ArrayList<String> UserDetails = UserFunctions.GetUserDetails(UserID);

        DisplayUserDetails(UserDetails);
    }

    //Confirm if the user wants to go back if the button is pressed.
    public void button_Back(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Confirm exit");
        alertDialogBuilder
                .setMessage("Are you sure you wish to go back? All changes will be discarded.")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        Intent intent = new Intent(EditUserDetails.this, AccountDetails.class);
                        intent.putExtra("UserID", UserID);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void button_Submit(View view)
    {
        if (ValidateForm())
        {
            Intent intent = new Intent(EditUserDetails.this, AccountDetails.class);
            intent.putExtra("UserID", UserID);
            startActivity(intent);
        }
    }

    public void DisplayUserDetails(ArrayList<String> UserDetails)
    {
        TextView FirstName = (TextView)findViewById(R.id.EditText_EditFirstname);
        FirstName.setText(UserDetails.get(0));

        TextView LastName = (TextView)findViewById(R.id.EditText_EditLastName);
        LastName.setText(UserDetails.get(1));

        TextView Email = (TextView)findViewById(R.id.EditText_EditEmail);
        Email.setText(UserDetails.get(2));

        TextView Age = (TextView)findViewById(R.id.EditText_EditAge);
        Age.setText(UserDetails.get(3));

        RadioButton GenderFemale = (RadioButton)findViewById(R.id.RadioButton_EditFemale);
        RadioButton GenderMale = (RadioButton)findViewById(R.id.RadioButton_EditMale);
        RadioButton GenderOther = (RadioButton)findViewById(R.id.RadioButton_EditOther);

        String Gender = UserDetails.get(4);

        if (Gender.equals("Male"))
        {
            GenderMale.setChecked(true);
        }
        else if (Gender.equals("Female"))
        {
            GenderFemale.setChecked(true);
        }
        else if (Gender.equals("Other"))
        {
            GenderOther.setChecked(true);
        }
    }

    private boolean ValidateForm()
    {
        boolean ValidationSuccessful = true;

        String InvalidMessage = "";

        //Convert the contents of the text boxes to strings
        TextView FirstName = (TextView)findViewById(R.id.EditText_EditFirstname);
        TextView LastName = (TextView)findViewById(R.id.EditText_EditLastName);
        TextView Email = (TextView)findViewById(R.id.EditText_EditEmail);
        TextView Age = (TextView)findViewById(R.id.EditText_EditAge);

        String FName = FirstName.getText().toString();
        String LName = LastName.getText().toString();
        String TheEmail = Email.getText().toString();
        String TheAge = Age.getText().toString();

        //Validation dialogue
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Invalid Edits");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        //No action to be taken until validation issue is resolved.
                    }
                });

        if (FName.equals("") && LName.equals(""))
        {
            ValidationSuccessful = false;
            InvalidMessage = "You need to enter either a First Name or a Last Name.";
            alertDialogBuilder.setMessage(InvalidMessage);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        if (TheEmail.equals("") && ValidationSuccessful)
        {
            ValidationSuccessful = false;
            InvalidMessage = "You must enter an Email Address.";
            alertDialogBuilder.setMessage(InvalidMessage);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        if (!Common.IsEmailValid(TheEmail) && !TheEmail.equals("") && ValidationSuccessful)
        {
            ValidationSuccessful = false;
            InvalidMessage = "Email Address is invalid.";
            alertDialogBuilder.setMessage(InvalidMessage);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        //Note: User input will normally prevent most of these errors in the first place.
        //But just in case validate it.
        else if (!Common.isNumeric(TheAge))
        {
            if (!TheAge.equals(""))
            {
                ValidationSuccessful = false;
                InvalidMessage = "Age must be a number.";
                alertDialogBuilder.setMessage(InvalidMessage);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
        else
        {
            if (!TheAge.equals(""))
            {
                int AgeInt = Integer.parseInt(TheAge);
                if (AgeInt < 1)
                {
                    ValidationSuccessful = false;
                    InvalidMessage = "Age must be a positive number greater than zero.";
                    alertDialogBuilder.setMessage(InvalidMessage);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        }
        return ValidationSuccessful;
    }
}

package com.example.kirmi.ks1807;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;

public class CurrentMusic extends AppCompatActivity
{
    private final Context context = this;
    CommonFunctions Common = new CommonFunctions();
    CurrentMusicCustomFunctions MusicFunctions = new CurrentMusicCustomFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_music);

        //Get the UserID for this login session.
        Intent intent = getIntent();
        String UserID = intent.getStringExtra("UserID");

        String[] UserDetails;
        UserDetails = MusicFunctions.GetMusicHistory(UserID);
        DisplayUserName(UserDetails);

        /*Check if the user has a prior history of listening to music through this app.
        If not then make the history fields invisible*/
        if (UserDetails.length != 0)
        {
            DisplayMusicHistory(UserDetails);
        }
        else
        {
            TextView NoMusicHistory = (TextView)findViewById(R.id.Text_NoMusicHistory);
            NoMusicHistory.setVisibility(View.VISIBLE);

            LinearLayout MusicHistoryGroup = (LinearLayout)findViewById(R.id.Layout_MusicHistoryGroup);
            MusicHistoryGroup.setVisibility(View.GONE);
        }
    }

    public void button_Logout(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Confirm logout");
        alertDialogBuilder
                .setMessage("Are you sure that you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        Intent intent = new Intent(CurrentMusic.this, MainActivity.class);
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

    public void button_ChangePassword(View view)
    {
        Intent intent = new Intent(CurrentMusic.this, ChangePassword.class);
        startActivity(intent);
    }

    public void button_EditUserDetails(View view)
    {
        Intent intent = new Intent(CurrentMusic.this, EditUserDetails.class);
        startActivity(intent);
    }

    public void DisplayUserName(String[] UserDetails)
    {
        TextView WelcomeUser = (TextView)findViewById(R.id.Text_WelcomeUser);
        WelcomeUser.setText(getResources().getString(R.string.WelcomeUserDefaultCaption)
                + ": " + UserDetails[0] + " " + UserDetails[1] + "!");
    }

    public void DisplayMusicHistory(String[] UserDetails)
    {
        TextView TrackName = (TextView)findViewById(R.id.Text_TrackNameDisplay);
        TrackName.setText(UserDetails[2]);

        TextView TrackGenre = (TextView)findViewById(R.id.Text_TrackGenreDisplay);
        TrackGenre.setText(UserDetails[3]);

        TextView TrackArtist = (TextView)findViewById(R.id.Text_TrackArtistDisplay);
        TrackArtist.setText(UserDetails[4]);

        TextView TrackLength = (TextView)findViewById(R.id.Text_TrackLengthDisplay);
        TrackLength.setText(UserDetails[5]);

        TextView MoodBefore = (TextView)findViewById(R.id.Text_TrackMoodBeforeDisplay);
        MoodBefore.setText(UserDetails[6]);

        TextView MoodAfter = (TextView)findViewById(R.id.Text_TrackMoodAfterDisplay);
        MoodAfter.setText(UserDetails[7]);
    }

}

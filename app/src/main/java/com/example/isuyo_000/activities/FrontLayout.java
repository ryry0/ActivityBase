package com.example.isuyo_000.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by isuyo_000 on 7/31/2017.
 */

public class FrontLayout extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_layout);
        //retrieve stored data through Intent
        //int age = getIntent().getIntExtra("user-age", -1);

        User p = (User) getIntent().getExtras().get("selectedUser");

        user = getIntent().getParcelableExtra("selectedUser");

        Button toUsersScreen = (Button) findViewById(R.id.toUsers);

        toUsersScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(getApplicationContext(), UserList.class);
                intent.putExtra("userSelected", user);
                //TODO normalize default user in program
                startActivity(intent);
            }
        });

        Button toSettingsScreen = (Button) findViewById(R.id.toSettings);

        toSettingsScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(getApplicationContext(), ChannelLimits.class);
                intent.putExtra("userSelected", user);
                //TODO normalize default user in program
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            if (data.hasExtra("selectedUser")) {
                user = (data.getExtras().getParcelable("selectedUser"));
                Toast.makeText(
                        this,
                        "User found is :  " + user.getID(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Returns User selected to MainActivity
    @Override
    public void onBackPressed(){
        Intent data = new Intent();
        data.putExtra("selectedUser", user);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        finish();
    }


}

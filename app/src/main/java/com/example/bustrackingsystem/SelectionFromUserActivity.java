package com.example.bustrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionFromUserActivity extends AppCompatActivity {
    Button btn_driver;
    Button btn_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_from_user);
        btn_driver =  findViewById(R.id.btn_driver);
        btn_client =  findViewById(R.id.btn_client);



        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectionFromUserActivity.this, DriverActivity.class);
                startActivity(i);
            }
        });


        btn_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectionFromUserActivity.this , ClientActivity.class);
                startActivity(i);
            }
        });



    }
}

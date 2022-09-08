package com.example.crudsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd, buttonViewAll;
    TextView username, password;
    ListView listViewuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.addData);
        buttonViewAll = findViewById(R.id.showData);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        listViewuser = findViewById(R.id.listView);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            userModel userModel;

            @Override
            public void onClick(View v) {
                try {
                    userModel = new userModel(0,username.getText().toString(), password.getText().toString());
                    //Toast.makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                dbHelper.addUser(userModel);
            }
        });

        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                ArrayList<userModel> list = dbHelper.getAllUsers();
                userAdapter arrayAdapter = new userAdapter
                        (MainActivity.this, android.R.layout.simple_list_item_1, list);
                listViewuser.setAdapter(arrayAdapter);
            }
        });

    }
}
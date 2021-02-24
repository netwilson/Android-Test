package com.infoki.sales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3- Create a ListView with Scroll to show Code and Name of each Client, the dao has the method loadClientsList to get this data retriven an ArrayList of Clients

        // 4- When we click on a client in the list, it has to shows the data of the clients (code, name, quote and notes) on the screen
    }
}

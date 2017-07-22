package com.inti.student.androidassignment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new API_Fragment()).commit(); //Calls a new API_Fragment class
                    return true;
                case R.id.navigation_history:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HistoryFragment()).commit(); //Calls a new History_Fragment class
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new API_Fragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

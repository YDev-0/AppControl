package com.control.appcontrol;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;
    private DrawerLayout mainDrawer;
    private NavigationView mainMenu;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = findViewById(R.id.mainToolbar);
        mainDrawer = findViewById(R.id.mainDrawer);
        mainMenu = findViewById(R.id.mainMenu);

        setSupportActionBar(mainToolbar);
        toggle = new ActionBarDrawerToggle(this, mainDrawer, mainToolbar, R.string.open, R.string.close);
        mainDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }
}
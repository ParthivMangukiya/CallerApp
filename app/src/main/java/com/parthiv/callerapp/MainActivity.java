package com.parthiv.callerapp;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private static final int RC_CONTACT_LOG = 111;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    RecyclerView contactsRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        loadActivity();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_import_export_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_phone_book);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_CONTACT_LOG)
    private void loadActivity() {

        String[] perms = new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.READ_CALL_LOG};
        if (EasyPermissions.hasPermissions(this, perms)) {
            viewPager = findViewById(R.id.viewpager);
            tabLayout = findViewById(R.id.tabs);
            MainActivityPagerAdapter mainActivityPagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(mainActivityPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()){
                        case 0:
                            toolbar.setTitle("Logs");
                            break;
                        case 1:
                            toolbar.setTitle("Contacts");
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            setupTabIcons();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.contact_rationale),
                    RC_CONTACT_LOG, perms);
        }
    }
}

package com.example.ocenitapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Member_click extends AppCompatActivity{
    android.support.v7.widget.Toolbar toolbar;
    ActionBar actionBar;
    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView img;
    TextView member_click_textview1;
    TextView member_click_affiliation;
    TextView member_click_call;
    TextView member_click_mail;
    TextView member_click_bachelor;
    TextView member_click_bachelor_major;
    TextView member_click_bachelor_degree;
    TextView member_click_master;
    TextView member_click_master_major;
    TextView member_click_master_degree;
    TextView member_click_doctor;
    TextView member_click_doctor_major;
    TextView member_click_doctor_degree;
    String[] intent_string = {" ", " ", " ", " ", " ", " ", " ", " "," ", " ", " ", " ", " ", " ", " ", " " };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_click);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.nav_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawer.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_Introduce:
                        Toast.makeText(Member_click.this, "같은 메뉴 입니다.", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Member:
                        Toast.makeText(Member_click.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Research_Field:
                        Toast.makeText(Member_click.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_Research_achievement:
                        Toast.makeText(Member_click.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_question:
                        Toast.makeText(Member_click.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                }

                return true;
            }
        });

        Intent intent = getIntent();

        member_click_textview1 = (TextView)findViewById(R.id.member_click_text1);
        intent_string[0] = intent.getExtras().getString("name");
        member_click_textview1.setText(intent_string[0]);

        img = (ImageView)findViewById(R.id.member_click_img);
        intent_string[1] = intent.getExtras().getString("img");
        String packName = this.getPackageName(); // 패키지명
        int resID = getResources().getIdentifier(intent_string[1], "drawable", packName);
        img.setImageResource(resID);

        member_click_affiliation = findViewById(R.id.member_click_affiliation);
        intent_string[2] = intent.getExtras().getString("affiliation");
        member_click_affiliation.setText(intent_string[2]);

        member_click_call = findViewById(R.id.member_click_call);
        intent_string[3] = intent.getExtras().getString("call");
        member_click_call.setText(intent_string[3]);

        member_click_mail = findViewById(R.id.member_click_email);
        intent_string[4] = intent.getExtras().getString("mail");
        member_click_mail.setText(intent_string[4]);

        member_click_bachelor = findViewById(R.id.tb_bachelor);
        intent_string[5] = intent.getExtras().getString("bachelor");
        member_click_bachelor.setText(intent_string[5]);

        member_click_bachelor_major = findViewById(R.id.tb_bachelor_magor);
        intent_string[6] = intent.getExtras().getString("bachelor_major");
        member_click_bachelor_major.setText(intent_string[6]);

        member_click_bachelor_degree = findViewById(R.id.tb_bachelor_degree);
        intent_string[7] = intent.getExtras().getString("bachelor_degree");
        member_click_bachelor_degree.setText(intent_string[7]);

        member_click_master = findViewById(R.id.tb_master);
        intent_string[8] = intent.getExtras().getString("master");
        member_click_master.setText(intent_string[8]);

        member_click_master_major = findViewById(R.id.tb_master_major);
        intent_string[9] = intent.getExtras().getString("master_major");
        member_click_master_major.setText(intent_string[9]);

        member_click_master_degree = findViewById(R.id.tb_master_degree);
        intent_string[10] = intent.getExtras().getString("master_degree");
        member_click_master_degree.setText(intent_string[10]);

        member_click_doctor = findViewById(R.id.tb_doctor);
        intent_string[11] = intent.getExtras().getString("doctor");
        member_click_doctor.setText(intent_string[11]);

        member_click_doctor_major = findViewById(R.id.tb_doctor_major);
        intent_string[12] = intent.getExtras().getString("doctor_major");
        member_click_doctor_major.setText(intent_string[12]);

        member_click_doctor_degree = findViewById(R.id.tb_doctor_degree);
        intent_string[13] = intent.getExtras().getString("doctor_degree");
        member_click_doctor_degree.setText(intent_string[13]);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

package com.example.ocenitapp;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Member extends AppCompatActivity{
    android.support.v7.widget.Toolbar toolbar;
    ActionBar actionBar;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

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
                        Toast.makeText(Member.this, "같은 메뉴 입니다.", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Member:
                        Toast.makeText(Member.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Research_Field:
                        Toast.makeText(Member.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_Research_achievement:
                        Toast.makeText(Member.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_question:
                        Toast.makeText(Member.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                }

                return true;
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview4);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Item_member> items = new ArrayList<>();
        Item_member[] item = new Item_member[10];
        item[0] = new Item_member(R.drawable.p1, "이름 : 조용갑", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-8178", "Mail : ykcho@hoseo.edu");
        item[1] = new Item_member(R.drawable.p2, "이름 : 고학림", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-5691", "Mail : hlko@hoseo.edu");
        item[2] = new Item_member(R.drawable.p3, "이름 : 임태호", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-9642", "Mail : taehoim@hoseo.edu");
        item[3] = new Item_member(R.drawable.p4, "이름 : 조용호", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-5947", "Mail : ykcho@hoseo.edu");
        item[4] = new Item_member(R.drawable.p5, "이름 : 김계원", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-9831", "Mail : kwkim@hoseo.edu");
        item[5] = new Item_member(R.drawable.p6, "이름 : 김민상", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-9565", "Mail : minsang@hoseo.edu");
        item[6] = new Item_member(R.drawable.p7, "이름 : 차민혁", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-9565", "Mail : dwmh86@hoseo.edu");
        item[7] = new Item_member(R.drawable.p8, "이름 : 김길용", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-9565", "Mail : kykim@hoseo.edu");
        item[8] = new Item_member(R.drawable.p9, "이름 : 김세연", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-9565", "Mail : seyeon92@hoseo.edu");
        item[9] = new Item_member(R.drawable.p10, "이름 : 마현", "소속 : 해양IT융합기술연구소", "구내번호 : 041-540-9566", "Mail : 140172@hoseo.edu");

        for (int i = 0; i < 10; i++) {
            items.add(item[i]);
        }
        recyclerView.setAdapter(new RecyclerAdapter_member(getApplicationContext(), items, R.layout.activity_member));

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

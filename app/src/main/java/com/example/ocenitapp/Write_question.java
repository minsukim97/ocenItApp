package com.example.ocenitapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class Write_question extends AppCompatActivity{
    android.support.v7.widget.Toolbar toolbar;
    ActionBar actionBar;
    DrawerLayout drawer;
    NavigationView navigationView;
    AutoScrollViewPager autoViewPager;
    ArrayList<String> data = new ArrayList<>();

    final int ITEM_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_question);

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
                        Intent introduce_intent = new Intent(Write_question.this, Introduce.class);
                        startActivity(introduce_intent);
                        break;

                    case R.id.navigation_item_Member:
                        Intent member_intent = new Intent(Write_question.this, Member.class);
                        startActivity(member_intent);
                        break;

                    case R.id.navigation_item_Progress_task:
                        Intent research_progress_task_intent = new Intent(Write_question.this, Progresstask.class);
                        startActivity(research_progress_task_intent);
                        break;

                    case R.id.navigation_item_Completion_task:
                        Intent research_completaion_task_intent = new Intent(Write_question.this, Research_Field.class);
                        startActivity(research_completaion_task_intent);
                        break;

                    case R.id.navigation_item_thesis:
                        Intent thesis_intent = new Intent(Write_question.this, Thesis.class);
                        startActivity(thesis_intent);
                        break;

                    case R.id.navigation_item_patent:
                        Intent patent_intent = new Intent(Write_question.this, Patent.class);
                        startActivity(patent_intent);
                        break;

                    case R.id.nav_item_question:
                        Intent question_intent = new Intent(Write_question.this, Question.class);
                        startActivity(question_intent);
                        break;

                }

                return true;
            }
        });

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

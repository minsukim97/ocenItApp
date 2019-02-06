package com.example.ocenitapp;

import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;

import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
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
    EditText et_title;
    EditText et_writer;
    EditText et_in;
    String send_writer;
    String send_title;
    String send_in;
    Button button2;
    phpdo task;
    String today = null;

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

        et_title = (EditText) findViewById(R.id.et_title);
        et_writer = (EditText) findViewById(R.id.et_writer);
        et_in =  (EditText) findViewById(R.id.et_in);

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
                Calendar cal = Calendar.getInstance();
                today = formatter.format(cal.getTime());

                send_title = et_title.getText().toString();
                send_writer = et_writer.getText().toString();
                send_in = et_in.getText().toString();

                Log.e("title", send_title);
                Log.e("writer", send_writer);
                Log.e("in", send_in);
                Log.e("date", String.valueOf(today));
                isPlayingThread();
                task = new phpdo();
                task.execute();

                Intent write_intent = new Intent(Write_question.this, Question.class);
                write_intent.setFlags(write_intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(write_intent);
            }
        });

    }

    private void isPlayingThread() {
        try {
            if (task.getStatus() == AsyncTask.Status.RUNNING) {
                task.cancel(true);
                task = null;
            }
        } catch (Exception e) {
        }
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

    private class phpdo extends AsyncTask<String, Void, String> {
        String errorString = null;

        protected void onPreExecute() {
        }

        // 백그라운드에서 json형식 값 받는다.
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String link = "http://210.119.107.82/html/graph/board_insert.php";
                String postParameters = "Title=" + "'" + send_title + "'" + "&Writer=" + "'" + send_writer + "'" + "&Count=" + 0 + "&Content=" + "'" + send_in + "'" ;

                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();

                OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
                wr.write(postParameters);
                wr.flush();
                wr.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d("response: ","response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();

                Log.e("print", sb.toString().trim());
                return sb.toString().trim();


            } catch (Exception e) {

                Log.d("error", "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }
        }

        //결과 부분
        @Override
        protected void onPostExecute(String result) {

        }
    }

}

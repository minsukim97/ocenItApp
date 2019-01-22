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

public class MainActivity extends AppCompatActivity{
    android.support.v7.widget.Toolbar toolbar;
    ActionBar actionBar;
    DrawerLayout drawer;
    NavigationView navigationView;
    AutoScrollViewPager autoViewPager;
    ArrayList<String> data = new ArrayList<>();
    ListView mListView, mListView1;
    phpdo task;
    phpdo_s task_s;

    ArrayList<String> c_classify = new ArrayList<String>();
    ArrayList<String> KORName = new ArrayList<String>();
    ArrayList<String> c_charge = new ArrayList<String>();

    ArrayList<String> t_name = new ArrayList<String>();
    ArrayList<String> author = new ArrayList<String>();
    ArrayList<String> t_date = new ArrayList<String>();



    final int ITEM_SIZE = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isPlayingThread();
        task = new phpdo();
        task.execute();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.nav_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        isPlayingThread_s();
        task_s = new phpdo_s();
        task_s.execute();

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
                        Intent introduce_intent = new Intent(MainActivity.this, Introduce.class);
                        startActivity(introduce_intent);
                        break;

                    case R.id.navigation_item_Member:
                        Intent member_intent = new Intent(MainActivity.this, Member.class);
                        startActivity(member_intent);
                        break;
                        
                    case R.id.navigation_item_Progress_task:
                        Intent research_progress_task_intent = new Intent(MainActivity.this, Progresstask.class);
                        startActivity(research_progress_task_intent);
                        break;

                    case R.id.navigation_item_Completion_task:
                        Intent research_completaion_taskk_intent = new Intent(MainActivity.this, Research_Field.class);
                        startActivity(research_completaion_taskk_intent);
                        break;

                    case R.id.nav_item_Research_achievement:
                        Intent test = new Intent(MainActivity.this, test.class);
                        startActivity(test);
                        break;

                    case R.id.nav_item_question:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                }

                return true;
            }
        });

        data.add("http://oceanic-ict.hoseo.ac.kr/images/layout/banner1.png");
        data.add("http://oceanic-ict.hoseo.ac.kr/images/layout/banner2.png");
        data.add("http://oceanic-ict.hoseo.ac.kr/images/layout/banner3.png");


        autoViewPager = (AutoScrollViewPager)findViewById(R.id.autoViewPager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(this, data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(5000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager1);

        List<Item> items = new ArrayList<>();
        List<Item> items1 = new ArrayList<>();

        Item[] item = new Item[ITEM_SIZE];
        Item[] item1 = new Item[ITEM_SIZE];

        item[0] = new Item(R.drawable.a, "5G 산업별 딥러닝 모형 개발");
        item[1] = new Item(R.drawable.a, "분산형 수중관측 제어망 개발");
        item[2] = new Item(R.drawable.a, "수중광역 이동통신시스템 기술 개발");

        item1[0] = new Item(R.drawable.b, "해양전자통신연구");
        item1[1] = new Item(R.drawable.b, "해양전자통신연구");

        for (int i = 0; i < 3; i++) {
            items.add(item[i]);
        }
        for (int i = 0; i < 2; i++) {
            items1.add(item1[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
        recyclerView1.setAdapter(new RecyclerAdapter(getApplicationContext(), items1, R.layout.activity_main));

        mListView = (ListView)findViewById(R.id.listView);
        mListView1 = (ListView)findViewById(R.id.listView1);
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

    private void isPlayingThread() {
        try {
            if (task.getStatus() == AsyncTask.Status.RUNNING) {
                task.cancel(true);
                task = null;
            }
        } catch (Exception e) {
        }
    }

    private void isPlayingThread_s() {
        try {
            if (task_s.getStatus() == AsyncTask.Status.RUNNING) {
                task_s.cancel(true);
                task_s = null;
            }
        } catch (Exception e) {
        }
    }

    private class phpdo extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        // 백그라운드에서 json형식 값 받는다.
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String link = "http://210.119.107.82/html/graph/completedtask.php";
                URL url = new URL(link);

                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        //결과 부분
        @Override
        protected void onPostExecute(String result) {
            try {
                String test;
                //JSON 객체 생성 후 JSON 배열에 값을 넣음
                JSONObject jObject = new JSONObject(result);
                JSONArray results = jObject.getJSONArray("result");
                int length = results.length();

                if (length == 0) {
                    JSONException ex = new JSONException(result);
                    throw ex;
                }

                //json 형식으로 넘어온 것에서 키값이 title, content로 나눠서 어레이리스트에 저장
                for (int i = 0; i < length; i++) {
                    JSONObject temp = results.getJSONObject(i);
                    c_classify.add(temp.getString("f_classify"));
                    KORName.add(temp.getString("f_KORName"));
                    c_charge.add(temp.getString("f_charge"));
                }
                Log.e("phpod","출력 완료");

                MyAdapter mMyAdapter = new MyAdapter();

                for (int i=0; i<10; i++) {
                    if(i < length)
                    {
                        mMyAdapter.addItem(c_classify.get(i), KORName.get(i), c_charge.get(i));
                    }
                    else if(i > length && i < 10) {
                        mMyAdapter.addItem("-", "-", "-");
                    }
                }

                mListView.setAdapter(mMyAdapter);
            } catch (
                    JSONException e)

            {
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsStrting = sw.toString();
                Log.e("GODK", exceptionAsStrting);
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Database Connection Error");
                builder.setMessage("데이터가 없습니다.");
                builder.setNeutralButton("닫기", null);
                builder.show();
            }
        }
    }

    private class phpdo_s extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        // 백그라운드에서 json형식 값 받는다.
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String link = "http://210.119.107.82/html/graph/thesis.php";
                URL url = new URL(link);

                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        //결과 부분
        @Override
        protected void onPostExecute(String result) {
            try {
                String test;
                //JSON 객체 생성 후 JSON 배열에 값을 넣음
                JSONObject jObject = new JSONObject(result);
                JSONArray results = jObject.getJSONArray("result");
                int length = results.length();

                if (length == 0) {
                    JSONException ex = new JSONException(result);
                    throw ex;
                }

                //json 형식으로 넘어온 것에서 키값이 title, content로 나눠서 어레이리스트에 저장
                for (int i = 0; i < length; i++) {
                    JSONObject temp = results.getJSONObject(i);
                    t_name.add(temp.getString("KOR_tName"));
                    author.add(temp.getString("author"));
                    t_date.add(temp.getString("t_Date"));
                }

                MyAdapter_t mMyAdapter1 = new MyAdapter_t();

                for (int i=0; i<10; i++) {
                    if(i < length)
                    {
                        mMyAdapter1.addItem(t_name.get(i), author.get(i), t_date.get(i));
                    }
                    else if(i > length && i < 10) {
                        mMyAdapter1.addItem("-", "-", "-");
                    }
                }

                mListView1.setAdapter(mMyAdapter1);
            } catch (
                    JSONException e)

            {
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsStrting = sw.toString();
                Log.e("GODK", exceptionAsStrting);
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Database Connection Error");
                builder.setMessage("데이터가 없습니다.");
                builder.setNeutralButton("닫기", null);
                builder.show();
            }
        }
    }

}

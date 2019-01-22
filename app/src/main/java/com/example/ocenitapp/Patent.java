package com.example.ocenitapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class Patent extends AppCompatActivity implements AbsListView.OnScrollListener{
    android.support.v7.widget.Toolbar toolbar;
    ActionBar actionBar;
    DrawerLayout drawer;
    NavigationView navigationView;
    private ListView listView;                      // 리스트뷰
    private boolean lastItemVisibleFlag = false;    // 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    private List<String> list;                      // String 데이터를 담고있는 리스트
    //private ListViewAdapter adapter;                // 리스트뷰의 아답터
    private int page = 0;                           // 페이징변수. 초기 값은 0 이다.
    private final int OFFSET = 5;                  // 한 페이지마다 로드할 데이터 갯수.
    private ProgressBar progressBar;                // 데이터 로딩중을 표시할 프로그레스바
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수
    MyAdapter_patent mMyAdapter;
    phpdo task;

    ArrayList<String> p_KORName = new ArrayList<String>();
    ArrayList<String> p_Num = new ArrayList<String>();
    ArrayList<String> p_Date = new ArrayList<String>();
    ArrayList<String> inventor = new ArrayList<String>();
    ArrayList<String> nation = new ArrayList<String>();
    ArrayList<String> kind = new ArrayList<String>();


    ListView mListView;

    int length = 0;
    static  int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patent_main);

        isPlayingThread();
        task = new phpdo();
        task.execute();

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
                        Toast.makeText(Patent.this, "같은 메뉴 입니다.", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Member:
                        Toast.makeText(Patent.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Research_Field:
                        Toast.makeText(Patent.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_Research_achievement:
                        Toast.makeText(Patent.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_question:
                        Toast.makeText(Patent.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                }

                return true;
            }
        });

        listView = (ListView) findViewById(R.id.listview_r3);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        list = new ArrayList<String>();

        mMyAdapter = new MyAdapter_patent();
        listView.setAdapter(mMyAdapter);

        progressBar.setVisibility(View.GONE);

        listView.setOnScrollListener(this);


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

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // 1. OnScrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하지 않을때의 이벤트(즉 스크롤이 멈추었을때).
        // 2. lastItemVisibleFlag : 리스트뷰의 마지막 셀의 끝에 스크롤이 이동했을때.
        // 3. mLockListView == false : 데이터 리스트에 다음 데이터를 불러오는 작업이 끝났을때.
        // 1, 2, 3 모두가 true일때 다음 데이터를 불러온다.
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
            // 화면이 바닦에 닿을때 처리
            // 로딩중을 알리는 프로그레스바를 보인다.
            progressBar.setVisibility(View.VISIBLE);

            // 다음 데이터를 불러온다.
            getItem();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        // firstVisibleItem : 화면에 보이는 첫번째 리스트의 아이템 번호.
        // visibleItemCount : 화면에 보이는 리스트 아이템의 갯수
        // totalItemCount : 리스트 전체의 총 갯수
        // 리스트의 갯수가 0개 이상이고, 화면에 보이는 맨 하단까지의 아이템 갯수가 총 갯수보다 크거나 같을때.. 즉 리스트의 끝일때. true
        lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
    }

    private void getItem(){

        // 리스트에 다음 데이터를 입력할 동안에 이 메소드가 또 호출되지 않도록 mLockListView 를 true로 설정한다.
        mLockListView = true;

        // 다음 20개의 데이터를 불러와서 리스트에 저장한다.

        for(int i = 0; i < 5; i++){
            if(count >= length)
            {
                mMyAdapter.addItem("-", "-", "-", "-", "-", "-");
                count++;
            }
            else if(count < length)
            {
                if(count == length){
                    continue;
                }
                mMyAdapter.addItem(p_KORName.get(count), p_Num.get(count), p_Date.get(count), inventor.get(count), nation.get(count), kind.get(count));
                count++;

            }
        }

        // 1초 뒤 프로그레스바를 감추고 데이터를 갱신하고, 중복 로딩 체크하는 Lock을 했던 mLockListView변수를 풀어준다.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                mMyAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                mLockListView = false;
            }
        },1000);
    }

    @Override
    public void onBackPressed() {
        count = 0;
        super.onBackPressed();
    }

    private class phpdo extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        // 백그라운드에서 json형식 값 받는다.
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String link = "http://210.119.107.82/html/graph/patent.php";
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
                length = results.length();

                if (length == 0) {
                    JSONException ex = new JSONException(result);
                    throw ex;
                }

                //json 형식으로 넘어온 것에서 키값이 title, content로 나눠서 어레이리스트에 저장
                for (int i = 0; i < length; i++) {
                    JSONObject temp = results.getJSONObject(i);
                    p_KORName.add(temp.getString("p_KORName"));
                    //Log.e("classify",c_classify.toString());
                    p_Num.add(temp.getString("p_Num"));
                    //Log.e("KORName",KORName.toString());
                    p_Date.add(temp.getString("p_Date"));
                    //Log.e("classify",f_belong.toString());
                    inventor.add(temp.getString("inventor"));
                    //Log.e("classify",f_period.toString());
                    nation.add(temp.getString("nation"));
                    //Log.e("classify",c_charge.toString());
                    kind.add(temp.getString("kind"));
                    //Log.e("classify",c_charge.toString());
                }

                for (int i=0; i<5; i++) {
                    mMyAdapter.addItem(p_KORName.get(count), p_Num.get(count), p_Date.get(count), inventor.get(count), nation.get(count), kind.get(count));
                    count++;
                }
                getItem();

            } catch (
                    JSONException e)

            {
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsStrting = sw.toString();
                Log.e("GODK", exceptionAsStrting);
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Patent.this);
                builder.setTitle("Database Connection Error");
                builder.setMessage("데이터가 없습니다.");
                builder.setNeutralButton("닫기", null);
                builder.show();
            }
        }
    }

}

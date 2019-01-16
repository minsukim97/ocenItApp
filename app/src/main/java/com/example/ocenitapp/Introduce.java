package com.example.ocenitapp;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Introduce extends AppCompatActivity{
    android.support.v7.widget.Toolbar toolbar;
    ActionBar actionBar;
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView lntroduce_textview2;
    TextView Introduce_textview3;
    String introduce = "해양IT융합기술연구소 애플리케이션 방문을 진심으로 환영합니다.";
    String introduce_contents = "우리 연구소는 지난 1973년 개소하여 40여 년이 넘는 역사를 갖고 있으며 지난 2014년 1월 1일에는 호서대학교 해양IT융합기술연구소로 출범하게 되었습니다.\n" +
            "\n" +
            "우리 연구소는 선박해양플랜트 분야 기술발전의 선구자로서 원천기술개발과 응용 및 실용화 연구 등 종합 연구역량 수월성 확보를 통하여 국가 현안문제를 해결하고, 국제 표준을 선도하는 창조적 연구를 수행하여 국가적인 기술 수요와 정책수요에 부응하기 위해 노력하고 있습니다.\n" +
            "\n" +
            "이를 위해 친환경 미래선박 기술, 해양플랜트엔지니어링 기술, 해양사고 대응 및 해상 교통 체계 기술, 수중로봇 및 해양장비 기술 등을 중심으로 다양한 분야의 연구 활동을 수행하고 있습니다.\n" +
            "\n" +
            "그동안 국가 경제 성장의 원동력이 된 조선 산업과 신성장 먹거리 산업인 해양플랜트산업의 산업 경쟁력 강화와 국가 경쟁력 제고를 위하여 선박 해양플랜트 분야 전담 출연 연구소로서 관련 분야 기술 개발에 선도적 역할을 수행하고 국가정책 수립 및 국제 규제 대응에 선제적으로 대응하여 선박해양플랜트산업 발전의 밑거름이 되고, 국가 경제성장에 이바지하여 창조경제 실현에 기여할 수 있도록 최선의 노력을 다하겠습니다.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

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
                        Toast.makeText(Introduce.this, "같은 메뉴 입니다.", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Member:
                        Toast.makeText(Introduce.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigation_item_Research_Field:
                        Toast.makeText(Introduce.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_Research_achievement:
                        Toast.makeText(Introduce.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_item_question:
                        Toast.makeText(Introduce.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;

                }

                return true;
            }
        });

        lntroduce_textview2 = (TextView)findViewById(R.id.text2);
        SpannableStringBuilder sb = new SpannableStringBuilder(introduce);
        sb.setSpan(new ForegroundColorSpan(Color.parseColor("#0000FF")), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        lntroduce_textview2.setText(sb);

        Introduce_textview3 = (TextView)findViewById(R.id.text3);
        Introduce_textview3.setText(introduce_contents);

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

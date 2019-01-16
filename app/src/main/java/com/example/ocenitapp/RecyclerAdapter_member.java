package com.example.ocenitapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter_member extends RecyclerView.Adapter<RecyclerAdapter_member.ViewHolder> {
    Context context;
    List<Item_member> items;
    int item_layout;

    public RecyclerAdapter_member(Context context, List<Item_member> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_member, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item_member item = items.get(position);
        Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
        holder.image.setBackground(drawable);
        holder.name.setText(item.getName());
        holder.division.setText(item.getDivision());
        holder.number.setText(item.getNumber());
        holder.mail.setText(item.getMail());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = item.getName();
                Log.e("test", test);
                    switch(item.getName()) {
                        case "이름 : 조용갑":
                            Log.e("test", "in");
                            Intent intent = new Intent(context, Member_click.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("name", "조용갑 (Yong-Kap Cho)");
                            intent.putExtra("affiliation", "소속  :  해양IT공학전공/\n해양IT융합기술연구소");
                            intent.putExtra("call", "구내전화  :  041-540-5178");
                            intent.putExtra("mail", "E-mail  :  ykcho@hoseo.edu");
                            intent.putExtra("bachelor", "명지대");
                            intent.putExtra("bachelor_major", "-");
                            intent.putExtra("bachelor_degree", "학사");
                            intent.putExtra("master", "중앙대");
                            intent.putExtra("master_major", "물류정보");
                            intent.putExtra("master_degree", "경영학석사");
                            intent.putExtra("doctor", "한국해양대");
                            intent.putExtra("doctor_major", "해운및상해보험");
                            intent.putExtra("doctor_degree", "경영학박사");
                            intent.putExtra("img", "@drawable/p1");
                            context.startActivity(intent);
                            break;

                        case "이름 : 고학림":
                            Log.e("test", "in");
                            Intent intent1 = new Intent(context, Member_click.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent1.putExtra("name", "고학림 (Hak-Lim GO)");
                            intent1.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent1.putExtra("call", "구내전화  :  041-540-5691");
                            intent1.putExtra("mail", "E-mail  :  hlko@hoseo.edu");
                            intent1.putExtra("bachelor", "숭실대");
                            intent1.putExtra("bachelor_major", "전자공학");
                            intent1.putExtra("bachelor_degree", "공학사");
                            intent1.putExtra("master", "Fairlgh Dickinson Univ.");
                            intent1.putExtra("master_major", "전자공학");
                            intent1.putExtra("master_degree", "공학석사");
                            intent1.putExtra("doctor", "North Carolina State Univ.");
                            intent1.putExtra("doctor_major", "전기,컴퓨터공학");
                            intent1.putExtra("doctor_degree", "공학박사");
                            intent1.putExtra("img", "@drawable/p2");
                            context.startActivity(intent1);
                            break;

                        case "이름 : 임태호":
                            Log.e("test", "in");
                            Intent intent2 = new Intent(context, Member_click.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent2.putExtra("name", "임태호 (Tae-Ho Im)");
                            intent2.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent2.putExtra("call", "구내전화  :  041-540-9640");
                            intent2.putExtra("mail", "E-mail  :  taehoim@hoseo.edu");
                            intent2.putExtra("bachelor", "-");
                            intent2.putExtra("bachelor_major", "-");
                            intent2.putExtra("bachelor_degree", "-");
                            intent2.putExtra("master", "-");
                            intent2.putExtra("master_major", "-");
                            intent2.putExtra("master_degree", "-");
                            intent2.putExtra("doctor", "중앙대");
                            intent2.putExtra("doctor_major", "정보통신");
                            intent2.putExtra("doctor_degree", "박사");
                            intent2.putExtra("img", "@drawable/p3");
                            context.startActivity(intent2);
                            break;

                        case "이름 : 조용호":
                            Log.e("test", "in");
                            Intent intent3 = new Intent(context, Member_click.class);
                            intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent3.putExtra("name", "조용호 (Yong-Ho Cho)");
                            intent3.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent3.putExtra("call", "구내전화  :  041-540-5947");
                            intent3.putExtra("mail", "E-mail  :  ykcho@hoseo.edu");
                            intent3.putExtra("bachelor", "-");
                            intent3.putExtra("bachelor_major", "-");
                            intent3.putExtra("bachelor_degree", "-");
                            intent3.putExtra("master", "-");
                            intent3.putExtra("master_major", "-");
                            intent3.putExtra("master_degree", "-");
                            intent3.putExtra("doctor", "한국과학기술원");
                            intent3.putExtra("doctor_major", "전기및전자공학");
                            intent3.putExtra("doctor_degree", "박사");
                            intent3.putExtra("img", "@drawable/p4");
                            context.startActivity(intent3);
                            break;

                        case "이름 : 김계원":
                            Log.e("test", "in");
                            Intent intent4 = new Intent(context, Member_click.class);
                            intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent4.putExtra("name", "김계원 (Gye-Won Kim)");
                            intent4.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent4.putExtra("call", "구내전화  :  041-540-9831");
                            intent4.putExtra("mail", "E-mail  :  kwkim@hoseo.edu");
                            intent4.putExtra("bachelor", "호서대");
                            intent4.putExtra("bachelor_major", "정보통신공학");
                            intent4.putExtra("bachelor_degree", "학사");
                            intent4.putExtra("master", "호서대");
                            intent4.putExtra("master_major", "정보통신공학");
                            intent4.putExtra("master_degree", "석사");
                            intent4.putExtra("doctor", "호서대");
                            intent4.putExtra("doctor_major", "정보통신공학");
                            intent4.putExtra("doctor_degree", "박사");
                            intent4.putExtra("img", "@drawable/p5");
                            context.startActivity(intent4);
                            break;

                        case "이름 : 김민상":
                            Log.e("test", "in");
                            Intent intent5 = new Intent(context, Member_click.class);
                            intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent5.putExtra("name", "김민상 (Min-Sang Kim)");
                            intent5.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent5.putExtra("call", "구내전화  :  041-540-9565");
                            intent5.putExtra("mail", "E-mail  :  misang@hoseo.edu");
                            intent5.putExtra("bachelor", "호서대");
                            intent5.putExtra("bachelor_major", "정보통신공학");
                            intent5.putExtra("bachelor_degree", "학사");
                            intent5.putExtra("master", "호서대");
                            intent5.putExtra("master_major", "정보통신공학");
                            intent5.putExtra("master_degree", "석사");
                            intent5.putExtra("doctor", "호서대");
                            intent5.putExtra("doctor_major", "정보통신공학");
                            intent5.putExtra("doctor_degree", "박사");
                            intent5.putExtra("img", "@drawable/p6");
                            context.startActivity(intent5);
                            break;

                        case "이름 : 차민혁":
                            Log.e("test", "in");
                            Intent intent6 = new Intent(context, Member_click.class);
                            intent6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent6.putExtra("name", "차민혁 (Min-Hyeok Cha)");
                            intent6.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent6.putExtra("call", "구내전화  :  041-540-9565");
                            intent6.putExtra("mail", "E-mail  :  dwmh86@hoseo.edu");
                            intent6.putExtra("bachelor", "호서대");
                            intent6.putExtra("bachelor_major", "정보통신공학");
                            intent6.putExtra("bachelor_degree", "학사");
                            intent6.putExtra("master", "호서대");
                            intent6.putExtra("master_major", "정보통신공학");
                            intent6.putExtra("master_degree", "석사");
                            intent6.putExtra("doctor", "-");
                            intent6.putExtra("doctor_major", "-");
                            intent6.putExtra("doctor_degree", "-");
                            intent6.putExtra("img", "@drawable/p7");
                            context.startActivity(intent6);
                            break;

                        case "이름 : 김길용":
                            Log.e("test", "in");
                            Intent intent7 = new Intent(context, Member_click.class);
                            intent7.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent7.putExtra("name", "김길용 (Gill-Yong Kim)");
                            intent7.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent7.putExtra("call", "구내전화  :  041-540-9565");
                            intent7.putExtra("mail", "E-mail  :  kykim@hoseo.edu");
                            intent7.putExtra("bachelor", "호서대");
                            intent7.putExtra("bachelor_major", "정보통신공학");
                            intent7.putExtra("bachelor_degree", "학사");
                            intent7.putExtra("master", "-");
                            intent7.putExtra("master_major", "-");
                            intent7.putExtra("master_degree", "-");
                            intent7.putExtra("doctor", "-");
                            intent7.putExtra("doctor_major", "-");
                            intent7.putExtra("doctor_degree", "-");
                            intent7.putExtra("img", "@drawable/p8");
                            context.startActivity(intent7);
                            break;

                        case "이름 : 김세연":
                            Log.e("test", "in");
                            Intent intent8 = new Intent(context, Member_click.class);
                            intent8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent8.putExtra("name", "김세연 (Gill-Yong Kim)");
                            intent8.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent8.putExtra("call", "구내전화  :  041-540-9565");
                            intent8.putExtra("mail", "E-mail  :  seyeon92@hoseo.edu");
                            intent8.putExtra("bachelor", "호서대");
                            intent8.putExtra("bachelor_major", "정보통신공학");
                            intent8.putExtra("bachelor_degree", "학사");
                            intent8.putExtra("master", "-");
                            intent8.putExtra("master_major", "-");
                            intent8.putExtra("master_degree", "-");
                            intent8.putExtra("doctor", "-");
                            intent8.putExtra("doctor_major", "-");
                            intent8.putExtra("doctor_degree", "-");
                            intent8.putExtra("img", "@drawable/p9");
                            context.startActivity(intent8);
                            break;

                        case "이름 : 마현":
                            Log.e("test", "in");
                            Intent intent9 = new Intent(context, Member_click.class);
                            intent9.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent9.putExtra("name", "마현 (Ma Hyun)");
                            intent9.putExtra("affiliation", "소속  :  해양IT융합기술연구소");
                            intent9.putExtra("call", "구내전화  :  041-540-9565");
                            intent9.putExtra("mail", "E-mail  :  140172@hoseo.edu");
                            intent9.putExtra("bachelor", "호서대");
                            intent9.putExtra("bachelor_major", "정보통신공학");
                            intent9.putExtra("bachelor_degree", "학사");
                            intent9.putExtra("master", "호서대");
                            intent9.putExtra("master_major", "정보통신공학");
                            intent9.putExtra("master_degree", "석사");
                            intent9.putExtra("doctor", "-");
                            intent9.putExtra("doctor_major", "-");
                            intent9.putExtra("doctor_degree", "-");
                            intent9.putExtra("img", "@drawable/p10");
                            context.startActivity(intent9);
                            break;
                    }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView division;
        TextView number;
        TextView mail;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            division = (TextView) itemView.findViewById(R.id.division);
            number = (TextView) itemView.findViewById(R.id.number);
            mail = (TextView) itemView.findViewById(R.id.mail);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
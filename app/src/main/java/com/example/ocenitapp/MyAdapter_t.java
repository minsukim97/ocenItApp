package com.example.ocenitapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter_t extends BaseAdapter{

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<MyItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom_t, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView tv_kt = (TextView) convertView.findViewById(R.id.tv_KOR_tName) ;
        TextView tv_au = (TextView) convertView.findViewById(R.id.tv_author) ;
        TextView tv_td = (TextView) convertView.findViewById(R.id.tv_t_date) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_kt.setText(myItem.getKOR_tName());
        tv_kt.setMaxLines(1);
        tv_kt.setEllipsize(TextUtils.TruncateAt.END);

        tv_au.setText(myItem.getauthor());
        tv_au.setMaxLines(1);
        tv_au.setEllipsize(TextUtils.TruncateAt.END);

        tv_td.setText(myItem.gettdate());
        tv_td.setMaxLines(1);
        tv_td.setEllipsize(TextUtils.TruncateAt.END);

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String KOR_tName, String author, String t_date) {

        MyItem mItem = new MyItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setKOR_tName(KOR_tName);
        mItem.setauthor(author);
        mItem.settdate(t_date);
        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}
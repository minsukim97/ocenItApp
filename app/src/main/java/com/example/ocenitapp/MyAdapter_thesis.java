package com.example.ocenitapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter_thesis extends BaseAdapter{

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
            convertView = inflater.inflate(R.layout.listview_custom_thesis, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView tv_kn = (TextView) convertView.findViewById(R.id.tv_th_KOR_tName) ;
        TextView tv_tn = (TextView) convertView.findViewById(R.id.tv_th_t_name) ;
        TextView tv_at = (TextView) convertView.findViewById(R.id.tv_th_author) ;
        TextView tv_td = (TextView) convertView.findViewById(R.id.tv_th_t_Date) ;
        TextView tv_kd = (TextView) convertView.findViewById(R.id.tv_th_kind) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_kn.setText(myItem.getKOR_tName());
        tv_kn.setMaxLines(1);
        tv_kn.setEllipsize(TextUtils.TruncateAt.END);

        tv_tn.setText(myItem.gettname());
        tv_tn.setMaxLines(1);
        tv_tn.setEllipsize(TextUtils.TruncateAt.END);

        tv_at.setText(myItem.getauthor());
        tv_at.setMaxLines(1);
        tv_at.setEllipsize(TextUtils.TruncateAt.END);

        tv_td.setText(myItem.gettdate());
        tv_td.setMaxLines(1);
        tv_td.setEllipsize(TextUtils.TruncateAt.END);

        tv_kd.setText(myItem.getkind());
        tv_kd.setMaxLines(1);
        tv_kd.setEllipsize(TextUtils.TruncateAt.END);

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String KOR_tName, String t_name, String author, String t_Date, String kind) {

        MyItem mItem = new MyItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setKOR_tName(KOR_tName);
        mItem.settname(t_name);
        mItem.setauthor(author);
        mItem.settdate(t_Date);
        mItem.setkind(kind);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}
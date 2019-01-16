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

public class Research_field_listview_adapter extends BaseAdapter{

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
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView tv_tc = (TextView) convertView.findViewById(R.id.tv_task_category) ;
        TextView tv_tn = (TextView) convertView.findViewById(R.id.tv_task_name) ;
        TextView tv_rp = (TextView) convertView.findViewById(R.id.tv_research_period) ;
        TextView tv_sp = (TextView) convertView.findViewById(R.id.tv_support) ;
        TextView tv_rd = (TextView) convertView.findViewById(R.id.tv_research_director) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_tc.setText(myItem.getTask_category());
        tv_tn.setText(myItem.getTask_name());
        tv_tn.setMaxLines(1);
        tv_tn.setEllipsize(TextUtils.TruncateAt.END);
        tv_rp.setText(myItem.getResearch_period());
        tv_sp.setText(myItem.getsupport());
        tv_rd.setText(myItem.getResearch_director());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String task_category, String task_name, String research_period, String support,  String research_director) {

        MyItem mItem = new MyItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setTask_category(task_category);
        mItem.setTask_name(task_name);
        mItem.setResearch_period(research_period);
        mItem.setsupport(support);
        mItem.setResearch_director(research_director);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}
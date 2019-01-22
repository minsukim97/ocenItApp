package com.example.ocenitapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter_patent extends BaseAdapter{

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
            convertView = inflater.inflate(R.layout.listview_custom_patent, parent, false);
        }
        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView tv_pk = (TextView) convertView.findViewById(R.id.tv_pt_p_KORName) ;
        TextView tv_pn = (TextView) convertView.findViewById(R.id.tv_pt_p_Num) ;
        TextView tv_pd = (TextView) convertView.findViewById(R.id.tv_pt_p_Date) ;
        TextView tv_in = (TextView) convertView.findViewById(R.id.tv_pt_inventor) ;
        TextView tv_nt = (TextView) convertView.findViewById(R.id.tv_pt_nation) ;
        TextView tv_kd = (TextView) convertView.findViewById(R.id.tv_pt_kind) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_pk.setText(myItem.getp_KORName());
        tv_pk.setMaxLines(1);
        tv_pk.setEllipsize(TextUtils.TruncateAt.END);

        tv_pn.setText(myItem.getp_Num());
        tv_pn.setMaxLines(1);
        tv_pn.setEllipsize(TextUtils.TruncateAt.END);

        tv_pd.setText(myItem.getp_Date());
        tv_pd.setMaxLines(1);
        tv_pd.setEllipsize(TextUtils.TruncateAt.END);

        tv_in.setText(myItem.getinventor());
        tv_in.setMaxLines(1);
        tv_in.setEllipsize(TextUtils.TruncateAt.END);

        tv_nt.setText(myItem.getnation());
        tv_nt.setMaxLines(1);
        tv_nt.setEllipsize(TextUtils.TruncateAt.END);

        tv_kd.setText(myItem.getkind());
        tv_kd.setMaxLines(1);
        tv_kd.setEllipsize(TextUtils.TruncateAt.END);


        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String p_KORName, String p_Num, String p_Date, String inventor, String nation, String kind) {
        MyItem mItem = new MyItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setp_KORName(p_KORName);
        mItem.setp_Num(p_Num);
        mItem.setp_Date(p_Date);
        mItem.setinventor(inventor);
        mItem.setnation(nation);
        mItem.setkind(kind);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}
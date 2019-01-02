package com.example.q.final_tab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{

    LayoutInflater inflater = null;
    private ArrayList<ItemData> m_oData = null;
    private int nListCnt=0;

    public ListAdapter(ArrayList<ItemData> _oData){
        m_oData=_oData;
        nListCnt=m_oData.size();
    }

    @Override
    public int getCount(){
        Log.i("TAG","getCount");
        return nListCnt;
    }
    @Override
    public Object getItem(int position)
    {
        return m_oData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position , View convertView, ViewGroup parent){
        if (convertView==null){
            final Context context=parent.getContext();
            if (inflater==null){
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView oTextTitle = (TextView) convertView.findViewById(R.id.Name);
        TextView oTextDate = (TextView) convertView.findViewById(R.id.Number);
        Button oBtn = (Button) convertView.findViewById(R.id.btnSelector);
        ImageView img_flag= (ImageView)convertView.findViewById(R.id.img_flag);
        Button button3 = (Button) convertView.findViewById(R.id.button3);

        oTextTitle.setText(m_oData.get(position).name);
        oTextDate.setText(m_oData.get(position).number);
        oBtn.setOnClickListener(m_oData.get(position).onClickListener);
        img_flag.setImageResource( m_oData.get(position).getImgld() );
        button3.setOnClickListener(m_oData.get(position).onClickListener);











        convertView.setTag(""+position);
        return convertView;

    }








}

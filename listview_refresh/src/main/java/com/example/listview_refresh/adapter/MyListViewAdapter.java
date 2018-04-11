package com.example.listview_refresh.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listview_refresh.R;
import com.example.listview_refresh.bean.Person;

import java.util.List;

/**
 * Created by v-wangguobao-sy on 2016/8/10.
 */
public class MyListViewAdapter extends BaseAdapter {
    private List<Person> persons;
    Context context;

    public MyListViewAdapter(List<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (persons == null) ? 0 : persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView textViewItem01;
        TextView textViewItem02;
        TextView textViewItem03;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Person person = (Person) getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            Log.d("MyBaseAdapter", "新建convertView,position=" + position);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.textViewItem01 = (TextView) convertView.findViewById(
                    R.id.listView01Item01);
            viewHolder.textViewItem02 = (TextView) convertView.findViewById(
                    R.id.listView01Item02);
            viewHolder.textViewItem03 = (TextView) convertView.findViewById(
                    R.id.listView01Item03);

            //动态增加1个ImageView
            viewHolder.imageView = new ImageView(context);
            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            mParams.gravity = Gravity.CENTER;
            mParams.width = 50;
            viewHolder.imageView.setLayoutParams(mParams);
            //这个ImageView放到ListView的第2列之后
            ((LinearLayout) convertView).addView(viewHolder.imageView, 2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            Log.d("MyBaseAdapter", "旧的convertView,position=" + position);
        }

        viewHolder.textViewItem01.setText(String.valueOf(person.id));
        viewHolder.textViewItem02.setText(person.name);
        viewHolder.textViewItem03.setText(person.address);
        viewHolder.imageView.setImageResource(person.photo);

        //对ListView中第1个TextView配置OnClick事件
        viewHolder.textViewItem01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        "[textViewItem01.setOnClickListener]点击了" + person.name,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //对ListView中的每一行信息配置OnClick事件
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        "[convertView.setOnClickListener]点击了" + person.name,
                        Toast.LENGTH_SHORT).show();
            }

        });

        //对ListView中的每一行信息配置OnLongClick事件
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,
                        "[convertView.setOnLongClickListener]点击了" + person.name,
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return convertView;
    }

    public void addFrontData(List<Person> data) {
        persons.addAll(0, data);
        notifyDataSetChanged();
    }

    public void addData(List<Person> data) {
        persons.addAll(data);
        notifyDataSetChanged();
    }
}

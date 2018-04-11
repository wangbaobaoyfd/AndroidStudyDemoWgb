package com.wangguobao.retrofit_04_post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by v-wangguobao-sy on 2016/9/29.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<TngouBean.Cook> list;

    public MyAdapter(Context context, List<TngouBean.Cook> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        if(convertview ==null){
            convertview = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
            convertview.setTag(new ViewHolder(convertview));
        }
        ViewHolder holder = (ViewHolder) convertview.getTag();
        TngouBean.Cook cook = list.get(position);
        holder.title.setText(cook.getName());
        holder.info.setText(cook.getDescription());
        //图片加载使用的是Picasso
        Picasso.with(context).load("http://tnfs.tngou.net/image" + cook.getImg()).into(holder.image);
        return convertview;
    }

    public static class ViewHolder{

        private final ImageView image;
        private final TextView title;
        private final TextView info;

        public ViewHolder(View item){
            image = ((ImageView) item.findViewById(R.id.item_image));
            title = ((TextView) item.findViewById(R.id.item_title));
            info = ((TextView) item.findViewById(R.id.item_info));
        }
    }

    public void addAll(Collection<? extends TngouBean.Cook> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }

}

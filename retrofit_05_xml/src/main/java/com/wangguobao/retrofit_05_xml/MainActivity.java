package com.wangguobao.retrofit_05_xml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Channel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * http://www.inexus.co/portal.php?mod=rss&catid=
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.inexus.co")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Channel> channel = service.getChannel();
        channel.enqueue(this);
    }

    @Override
    public void onResponse(Call<Channel> call, Response<Channel> response) {
        Channel channel = response.body();
        //显示到textview
        String str = channel.getTitle() + "：\n\n";
        List<Item> list = channel.getList();//设置断点，查看信息
        for(Item item : list){
            str += item.getTitle() + "。\n";
        }
        TextView textView = (TextView) findViewById(R.id.tv);
        textView.setText(str);
    }

    @Override
    public void onFailure(Call<Channel> call, Throwable t) {
        t.printStackTrace();
    }
}

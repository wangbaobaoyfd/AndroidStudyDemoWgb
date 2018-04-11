package com.wangguobao.retrofit_02_tngou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<TngouBean> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * http://www.tngou.net/doc/cook/71是文档
         * http://www.tngou.net/api/cook/list是访问api地址
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<TngouBean> call = service.getList(0, 1, 5);//即http://www.tngou.net/api/cook/list?id=0&page=1&rows=5
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TngouBean> call, Response<TngouBean> response) {
        List<TngouBean.Cook> list = response.body().getList();//设置断点，查看信息
        String str = "";
        for(TngouBean.Cook cookList : list){
            str += cookList.getName() + ",\n";
        }
        ((TextView)findViewById(R.id.tv)).setText(str);//显示到Textview
    }

    @Override
    public void onFailure(Call<TngouBean> call, Throwable t) {
        t.printStackTrace();
    }
}

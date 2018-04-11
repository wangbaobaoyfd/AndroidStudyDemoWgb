package com.wangguobao.myretrofit;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<String> {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.baidu.com")
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, Object>() {
                            @Override
                            public Object convert(ResponseBody value) throws IOException {
                                return value.string();
                            }
                        };
                    }
                }).build();
        Service service = retrofit.create(Service.class);
        Call<String> call = service.getBaidu();
        call.enqueue(this);

        text = (TextView) findViewById(R.id.main_text);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        text.setText(response.body());//将请求到的百度首页body显示到Textview
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Toast.makeText(this,"请求失败"+ call.request().url(),Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }
}

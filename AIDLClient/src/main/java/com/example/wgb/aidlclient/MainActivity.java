package com.example.wgb.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wgb.aidlserver.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface myAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bt1 = (Button) findViewById(R.id.bt1);
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                //获得AIDL服务对象
                myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                bt1.setEnabled(true);//绑定完成后，使能Button
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        //绑定服务
        Intent intent = new Intent("com.example.wgb.aidlServer01");
        intent.setPackage("com.example.wgb.aidlserver");
        bindService(intent,connection,BIND_AUTO_CREATE);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = null;
                try {
                    msg = myAidlInterface.helloAndroidAIDL("Other Application");
                    Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

package com.example.wgb.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {//AIDL固定写法
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return new MyServiceImpl();//返回IMyAidlInterface.Stub子类对象
    }

    //内部类MyServiceImpl继承了AIDL工具生产的IMyAidlInterface.Stub接口，主要实现前面定义的接口helloAndroidAIDL
    public class MyServiceImpl extends IMyAidlInterface.Stub {
        @Override//helloAndroidAIDL接口具体实现，在服务端输出日志，返回字符串
        public String helloAndroidAIDL(String name) throws RemoteException {
            System.out.println("helloAndroidAIDL name = [" + name + "]");
            return "AIDL IMyAidlInterface return value";
        }
    }
}

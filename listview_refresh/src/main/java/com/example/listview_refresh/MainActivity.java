package com.example.listview_refresh;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview_refresh.adapter.MyListViewAdapter;
import com.example.listview_refresh.bean.Person;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MainActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    //定义Person
    List<Person> persons;
    MyListViewAdapter adapter = null;
    ListView mylistView = null;
    EditText editTextPersonName = null;
    Button bt_loadData;
    int dataFrontCount = 0;
    int dataCount = 0;
    private BGARefreshLayout mRefreshLayout;
    private int mMorePageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylistView = (ListView) findViewById(R.id.mylistview);
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.MyRefreshLayout);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getApplicationContext(), true));

        editTextPersonName = (EditText) findViewById(R.id.editText01Edit);
        bt_loadData = (Button) findViewById(R.id.bt_loadData);
        bt_loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadData();
            }
        });

        //显示ListView
        initListAllPersons();
        showByMyBaseAdapter();
    }

    public void initListAllPersons() {
        persons = new ArrayList<Person>();
        persons.add(new Person(100, "李小龙", "香港", android.R.drawable.ic_delete));
        persons.add(new Person(101, "施瓦辛格", "美国", android.R.drawable.ic_dialog_alert));
        persons.add(new Person(102, "戴安娜王妃", "英国", android.R.drawable.ic_dialog_dialer));
        persons.add(new Person(103, "成龙", "香港", android.R.drawable.ic_dialog_map));
        persons.add(new Person(104, "史泰龙", "美国", android.R.drawable.ic_lock_idle_alarm));
        persons.add(new Person(105, "圣女贞德", "法国", android.R.drawable.ic_media_next));
        persons.add(new Person(106, "列宁", "苏联", android.R.drawable.ic_media_play));
        persons.add(new Person(107, "爱迪生", "美国", android.R.drawable.ic_menu_add));
        persons.add(new Person(108, "孔子", "中国", android.R.drawable.ic_media_rew));
        persons.add(new Person(109, "杨七郎", "宋朝", android.R.drawable.ic_menu_zoom));
        persons.add(new Person(110, "秦始皇", "秦国", android.R.drawable.ic_menu_agenda));
        persons.add(new Person(111, "岳飞", "宋朝", android.R.drawable.ic_lock_lock));
    }
    private void LoadFrontData() {
        List<Person> newList = new ArrayList<Person>();
        for (int i = 0; i < 4; i++) {
            newList.add(new Person(dataFrontCount, "fcx" + dataFrontCount, "中国" + dataFrontCount, android.R.drawable.ic_menu_add));
            dataFrontCount++;
        }
        adapter.addFrontData(newList);
    }
    private void LoadData() {
        List<Person> newList = new ArrayList<Person>();
        for (int i = 0; i < 4; i++) {
            newList.add(new Person(dataCount, "wgb" + dataCount, "中国" + dataCount, android.R.drawable.ic_menu_add));
            dataCount++;
        }
        adapter.addData(newList);
    }


    public void showByMyBaseAdapter() {
        adapter = new MyListViewAdapter(persons, this);
        mylistView.setAdapter(adapter);
    }

    /**
     * 按钮button01Edit的onClick事件.
     *
     * @param view
     */
    public void editPersonAndRefreshListView01(View view) {
        //获取TextEdit数据
        String value = editTextPersonName.getText().toString();
        //更新ListView的内容，并且动态刷新.
        persons.get(0).name = value;
        ((BaseAdapter) adapter).notifyDataSetChanged();
    }

    /**
     * 初始化listView01的事件.
     */
    public void initListView01Event() {

        //ListView的item点击事件
        mylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "[OnItemClickListener]点击了：" + persons.get(position).name,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //ListView的item长按点击事件
        mylistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "[OnItemLongClickListener]点击了：" + persons.get(position).name,
                        Toast.LENGTH_SHORT).show();
                return false;
            }

        });

        //ListView的键盘选中事件(直接触摸屏幕选中不会激发)
        mylistView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "[OnItemSelectedListener:onItemSelected]点击了："
                                + persons.get(position).name,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),
                        "[OnItemSelectedListener:onNothingSelected]点击了",
                        Toast.LENGTH_SHORT).show();
            }

        });

    }

    /**
     * 开始刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadFrontData();//王国豹：下拉刷新的新数据
                mRefreshLayout.endRefreshing();
            }
        }, 1000);
    }

    /**
     * 开始加载更多。由于监听了ScrollView、RecyclerView、AbsListView滚动到底部的事件，所以这里采用返回boolean来处理是否加载更多。否则使用endLoadingMore方法会失效
     *
     * @param refreshLayout
     * @return 如果要开始加载更多则返回true，否则返回false。（返回false的场景：没有网络、一共只有x页数据并且已经加载了x页数据了）
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mMorePageNumber++;
        if (mMorePageNumber > 4) {
            mRefreshLayout.endLoadingMore();
            Toast.makeText(getApplicationContext(),"没有更多数据了end",Toast.LENGTH_SHORT).show();
            return false;
        }

        new AsyncTask<Void, Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(0); //延时1000
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // 加载完毕后在UI线程结束加载更多
                mRefreshLayout.endLoadingMore();
                //更新ListView的内容，并且动态刷新.
                LoadData();
/*                persons.add(persons.size(), new Person(dataCount, "wgb" + dataCount, "中国" + dataCount, android.R.drawable.ic_menu_add));
                ((BaseAdapter) adapter).notifyDataSetChanged();*/
            }
        }.execute();
        return true;
    }
}

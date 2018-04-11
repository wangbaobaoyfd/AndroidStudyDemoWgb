package com.example.listview_baseadapter.bean;

/**
 * Created by v-wangguobao-sy on 2016/8/10.
 */
public class Person {
    public int id;
    public String name;
    public String address;
    public int photo;

    public Person(int id, String name, String address,int photo) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.photo = photo;
    }
}

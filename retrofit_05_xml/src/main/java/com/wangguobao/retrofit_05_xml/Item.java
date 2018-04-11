package com.wangguobao.retrofit_05_xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by v-wangguobao-sy on 2016/9/29.
 */
@Root(strict = false)
public class Item {
    @Element(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }
}

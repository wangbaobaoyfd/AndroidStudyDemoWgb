package com.wangguobao.retrofit_05_xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by v-wangguobao-sy on 2016/9/29.
 */
@Root(strict = false) //由于我们只写了两个成语，所以不进行xml文件严格检查
public class Channel {
    @Path("channel") //由于title不是根目录
    @Element(name = "title")
    private String title;

    @Path("channel") //由于item不是根目录
    @ElementList(entry = "item" , inline = true)
    private List<Item> list;

    public List<Item> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }
}

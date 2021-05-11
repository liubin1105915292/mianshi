package com.luki.nacos;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * auther win
 * create 2021/5/7 0007 20:15
 **/
public class Test {
    public static void main(String[] args) {
        Person person = new Person();
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        list.add(map);
        List<Map<String,Object>> list2 = new ArrayList<>();
        list2.addAll(list);
        person.setList(list2);
        list.clear();
        System.out.println(person.getList());
    }
}

@Data
class Person{
    private List<Map<String,Object>> list;
}
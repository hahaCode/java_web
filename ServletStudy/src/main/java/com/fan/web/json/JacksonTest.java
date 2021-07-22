package com.fan.web.json;

import com.fan.web.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class JacksonTest {

    //Java对象转字符串
    @Test
    public void test1() throws IOException {
        Person p1 = new Person("Anna", 23, "female");

        //创建Jackson的核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //转换
        String jsonValue = mapper.writeValueAsString(p1);//将对象转为json字符串
        System.out.println(jsonValue);

        //还可以直接将转换后的json写入文件
        //mapper.writeValue(new File("d://json.txt"), p1);

    }

    @Test
    public void test2() throws IOException {
        Person p2 = new Person("Anna", 23, "female", new Date());
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(p2);
        System.out.println(s);
    }


    //复杂数据类型转换
    @Test
    public void test3() throws IOException {
        Person p1 = new Person("Anna", 23, "Female", new Date());
        Person p2 = new Person("Bob", 34, "Male", new Date());
        Person p3 = new Person("Nick", 12, "Male", new Date());

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(list);
        System.out.println(s);

        //[{"name":"Anna","age":23,"gender":"Female","birthday":"2021-06-24"},{"name":"Bob","age":34,"gender":"Male","birthday":"2021-06-24"},{"name":"Nick","age":12,"gender":"Male","birthday":"2021-06-24"}]
    }

    @Test
    public void test4() throws IOException {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "小黑");
        map.put("age", 23);
        map.put("gender", "男");

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(map);
        System.out.println(s);
    }

    //Json字符串转Java对象
    @Test
    public void test5() throws IOException {

        String json = "{\"gender\":\"男\",\"name\":\"小黑\",\"age\":23}";

        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);
    }
}

//https://www.bilibili.com/video/BV1qv4y1o79t?p=541&spm_id_from=pageDriver
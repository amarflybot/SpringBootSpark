package com.example;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amarendra on 11/04/17.
 */
@RestController
@RequestMapping("/api")
public class TestResource implements Serializable{


    @Autowired
    private SQLContext sqlContext;

    @GetMapping("/wb")
    @ResponseBody
    public void test(HttpServletResponse httpServletResponse) throws IOException {
        DataFrame dataFrame = sqlContext.read().json("src/main/resources/world_bank.json");
        PrintWriter writer = httpServletResponse.getWriter();
        dataFrame.printSchema();
        dataFrame.collectAsList().forEach(row -> {
            writer.write(String.valueOf(row));
            writer.flush();
        });
        writer.close();
    }

    @GetMapping("/com")
    @ResponseBody
    public void com(HttpServletResponse httpServletResponse) throws IOException {
        DataFrame dataFrame = sqlContext.read().json("src/main/resources/companies.json");
        PrintWriter writer = httpServletResponse.getWriter();
        dataFrame.printSchema();
        dataFrame.collectAsList().forEach(row -> {
            writer.write(String.valueOf(row));
            writer.flush();
        });
        writer.close();
    }

    @GetMapping
    @ResponseBody
    public void test1(HttpServletResponse httpServletResponse) throws IOException {
        DataFrame dataFrame = sqlContext.read().json("src/main/resources/people.json");
        PrintWriter writer = httpServletResponse.getWriter();
        List<String> list = new ArrayList<>();
        dataFrame.printSchema();
        dataFrame.javaRDD().foreach(new VoidFunction<Row>() {
            @Override
            public void call(Row row) throws Exception {
                System.out.println(row);
                list.add(String.valueOf(row));
            }
        });
        for (String str : list) {
            writer.write(str);
            writer.flush();
        }
        writer.close();
    }


}

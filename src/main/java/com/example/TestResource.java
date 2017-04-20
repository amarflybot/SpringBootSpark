package com.example;

import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

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
        final ObservableList<String, Row> olist = new ObservableList<>();
        dataFrame.printSchema();
        dataFrame.javaRDD().foreach(new VoidFunction<Row>() {
            @Override
            public void call(Row row) throws Exception {
                System.out.println(row);
                olist.add(String.valueOf(row));

            }
        });
        olist.getObservable().subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted Completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError Error");
            }

            @Override
            public void onNext(Object row) {
                System.out.println("onNext Next");
                //writer.write(String.valueOf(row));
                //writer.flush();
            }
        });
        writer.close();
    }


}

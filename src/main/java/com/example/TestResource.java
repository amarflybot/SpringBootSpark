package com.example;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by amarendra on 11/04/17.
 */
@RestController
public class TestResource {


    @Autowired
    private SQLContext sqlContext;

    @GetMapping
    public String test(){
        DataFrame dataFrame = sqlContext.read().json("src/main/resources/people.json");
        dataFrame.show();
        final String[] result = {"null"};
        dataFrame.collectAsList().forEach(row -> {
            System.out.println(row);
            result[0] = String.valueOf(row.get(0));
        });

        return result[0];
    }

}

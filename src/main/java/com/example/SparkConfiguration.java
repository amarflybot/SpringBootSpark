package com.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by amarendra on 10/04/17.
 */
@Configuration
public class SparkConfiguration {

    @Bean
    public SparkConf sparkConf(){
        return new SparkConf().setAppName("TestApp").setMaster("local[*]");
    }

    @Bean
    public JavaSparkContext sparkContext(final SparkConf sparkConf){
        return new JavaSparkContext(sparkConf);
    }

    @Bean
    public SQLContext sqlContext(final JavaSparkContext javaSparkContext){
        return new SQLContext(javaSparkContext);
    }

}

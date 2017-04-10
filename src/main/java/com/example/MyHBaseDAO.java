package com.example;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by amarendra on 11/04/17.
 */
public class MyHBaseDAO {

    public static void insertRecord(org.apache.hadoop.hbase.client.HTableInterface table, HBaseTestObj obj) throws IOException {

        Put put = new Put(Bytes.toBytes(obj.getRowKey()));
        put.add(Bytes.toBytes("CF"),Bytes.toBytes("CQ-1"),Bytes.toBytes(obj.getData1()));
        put.add(Bytes.toBytes("CF"),Bytes.toBytes("CQ-2"),Bytes.toBytes(obj.getData2()));
        table.put(put);
    }
}

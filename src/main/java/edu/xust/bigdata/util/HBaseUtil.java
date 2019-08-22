package edu.xust.bigdata.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HBaseUtil {
    private static  final Configuration conf = HBaseConfiguration.create();

    private HBaseUtil(){
    }

    static {
        conf.set("hbase.zookeeper.quorum", "node3:2181,node4:2181,node5:2181");
    }

    public static Connection getHbaseConn(){
        try {
            Connection conn = ConnectionFactory.createConnection(conf);
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

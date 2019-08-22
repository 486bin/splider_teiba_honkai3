package edu.xust.bigdata.dao;

import edu.xust.bigdata.domain.Post;
import edu.xust.bigdata.util.HBaseUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class PostDao {
    public void add(Post post) throws IOException {
        Connection conn = HBaseUtil.getHbaseConn();
        Table tb = conn.getTable(TableName.valueOf("honkai_3"));
        byte[] row = Bytes.toBytes(post.getTitle() + "," + post.getAuthor() + "," + System.currentTimeMillis());
        byte[] family = Bytes.toBytes("base_info");
        Put put = new Put(row);

        put.addColumn(family, Bytes.toBytes("title"), Bytes.toBytes(post.getTitle()));
        put.addColumn(family, Bytes.toBytes("author"), Bytes.toBytes(post.getAuthor()));
        StringBuffer sb = new StringBuffer();
        for (Object imageUrl : post.getImages()) {
            sb.append(String.valueOf(imageUrl) + ",");
        }
        String urls;
        if(post.getImages().size()>0)
            urls = sb.substring(0, sb.length() - 1);
        else
            urls = "";
        put.addColumn(family, Bytes.toBytes("images"), Bytes.toBytes(urls));
        tb.put(put);

        tb.close();
        conn.close();
    }
}

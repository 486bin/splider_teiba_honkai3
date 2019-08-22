package edu.xust.bigdata.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {

    /**
    * @Description: 下载url指向的网页页面
    * @Param: [url] 需要下载的页面的url地址
    * @return: java.lang.String 对应页面内容字符串
    * @Author: junhao you
    */
    public static String htmlDownload(String url) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            String html = EntityUtils.toString(response.getEntity());
            return html;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

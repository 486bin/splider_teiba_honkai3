package edu.xust.bigdata.download.impl;

import edu.xust.bigdata.download.DownLoader;
import edu.xust.bigdata.util.HttpUtil;

/**
 * 下载器类
 */
public class PostDownLoader implements DownLoader {

    public String downLoad(String url) {

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //测试阶段，下载页面休眠一秒
        String html = HttpUtil.htmlDownload(url);
        if (html != null)
            return html;
        else
            return null;
    }
}

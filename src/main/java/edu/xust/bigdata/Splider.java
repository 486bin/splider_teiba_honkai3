package edu.xust.bigdata;

import edu.xust.bigdata.domain.Post;
import edu.xust.bigdata.download.impl.PostDownLoader;
import edu.xust.bigdata.parse.impl.PostParser;
import edu.xust.bigdata.schedual.impl.PostSchedualForQueue;
import edu.xust.bigdata.schedual.impl.PostSchedualForRedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * splider主类
 */
public class Splider {
    public static void main(String[] args) {

        final PostDownLoader postDownLoader = new PostDownLoader();
        final PostParser postParser = new PostParser();
        final PostSchedualForRedis postSchedual = PostSchedualForRedis.getBdSchedual();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //此线程用于解析贴吧主页面中的帖子url地址
        executorService.execute(new Runnable() {
            public void run() {
                while (true) {
                    Long pageNum = postSchedual.getCurrentPageNum();
                    postSchedual.nextPage(50);
                    String html = postDownLoader.downLoad("https://tieba.baidu.com/f?kw=%E5%B4%A9%E5%9D%8F3rd&ie=utf-8&pn=" + pageNum);
                    postParser.parsePageList(html);
                }
            }
        });

        //此线程用于从url队列中获取帖子url地址，再进行解析获取Post对象
        executorService.execute(new Runnable() {
            public void run() {
                while (true) {
                    String pageUrl = postSchedual.pop();
                    if (pageUrl != null) {
                        //下载具体的页面
                        String pageHtml = postDownLoader.downLoad("https://tieba.baidu.com" + pageUrl);
                        //解析具体的页面的标题和图片
                        Post post = postParser.parseCurrentToPost(pageHtml);
                        System.out.println(post);
                    } else {

                    }
                }
            }
        });
    }
}

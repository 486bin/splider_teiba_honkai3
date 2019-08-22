package edu.xust.bigdata.parse.impl;

import edu.xust.bigdata.domain.Post;
import edu.xust.bigdata.parse.Parser;
import edu.xust.bigdata.schedual.Schedual;
import edu.xust.bigdata.schedual.impl.PostSchedualForQueue;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

/**
 * 解析器类
 */
public class PostParser implements Parser {
    private HtmlCleaner htmlCleaner = new HtmlCleaner();
    private Schedual schedual = PostSchedualForQueue.getPostSchedualForQueue();

    /**
    * @Description:  解析贴吧主页面对应的全部帖子的url地址，将其存放在调度器的url队列中
    * @Param: [html]
    * @return: void
    * @Author: junhao you
    */
    public void parsePageList(String html) {
        TagNode tagNode = htmlCleaner.clean(html);
        try {
            Object[] hrefs = tagNode.evaluateXPath("//a[@class='j_th_tit ']/@href");
            for (Object href : hrefs) {
                schedual.push(href.toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
    }

    /**
    * @Description:  解析帖子页面中的帖子标题、楼主昵称以及镇楼图url地址，并生成Post对象
    * @Param: [html]
    * @return: edu.xust.bigdata.domain.Post
    * @Author: junhao you
    */
    public Post parseCurrentToPost(String html) {
        TagNode tagNode = htmlCleaner.clean(html);
        Post post = new Post();
        try {
            Object[] titles = tagNode.evaluateXPath("//h3[@class='core_title_txt pull-left text-overflow  ']/text()");
            if (titles != null && titles.length>0){
                String title = titles[0].toString();
                post.setTitle(title);
            }

            Object[] authors = tagNode.evaluateXPath("//*[@id=\"j_p_postlist\"]/div[1]/div[1]/ul/li[3]/a/text()");
            if (authors != null && authors.length>0){
                String author = authors[0].toString();
                post.setAuthor(author);
            }

            Object[] imagesURL = tagNode.evaluateXPath("//img[@class='BDE_Image']/@src");
            for(Object imageURL: imagesURL){
                post.getImages().add(String.valueOf(imageURL));
            }
            return post;
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return null;
    }
}

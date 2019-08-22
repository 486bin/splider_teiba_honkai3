package edu.xust.bigdata.schedual.impl;

import edu.xust.bigdata.schedual.Schedual;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 调度器类
 */
public class PostSchedualForQueue implements Schedual {
    private long currentPageNum = 0L;
    //当前页面起始帖子位置
    private static final PostSchedualForQueue postSchedualForQueue = new PostSchedualForQueue();
    //单例模式获取url队列
    private Queue<String> queue = new ConcurrentLinkedQueue();
    //高并发的url队列存储页面url列表，交由调度器调度
    private PostSchedualForQueue() {
    }

    public static PostSchedualForQueue getPostSchedualForQueue() {
        return postSchedualForQueue;
    }


    public void push(String str) {
        queue.offer(str);
    }

    public String pop() {
        return queue.poll();
    }

    public Long getCurrentPageNum() {
        return currentPageNum;
    }

    public void nextPage(int pageNum){
        currentPageNum += pageNum;
    }
}

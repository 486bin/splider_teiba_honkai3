package edu.xust.bigdata.schedual.impl;

import edu.xust.bigdata.schedual.Schedual;
import edu.xust.bigdata.util.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * 使用Redis进行调度
 */
public class PostSchedualForRedis implements Schedual {
    private static final String CURRENTPAGENUM = "currentPageNum";
    private static final String PAGELIST = "pageList";
    private Long currentPageNum = 0L;
    private static PostSchedualForRedis bdSchedual = new PostSchedualForRedis();

    private PostSchedualForRedis() {
    }

    public static PostSchedualForRedis getBdSchedual() {
        return bdSchedual;
    }

    public void push(String str) {
        Jedis conn = JedisUtil.getJedisConn();
        conn.lpush(PAGELIST, str);
        conn.close();
    }

    public String pop() {
        Jedis conn = JedisUtil.getJedisConn();
        String page = conn.rpop(PAGELIST);
        conn.close();
        return page;
    }

    public Long getCurrentPageNum() {
        Jedis conn = JedisUtil.getJedisConn();
        String curr = conn.get(CURRENTPAGENUM);
        if (curr == null) {
            return currentPageNum;
        }
        conn.close();
        return Long.valueOf(curr);
    }

    public void nextPage(int pageNum){
        currentPageNum += pageNum;
        Jedis conn = JedisUtil.getJedisConn();
        conn.set(CURRENTPAGENUM, String.valueOf(currentPageNum));
        conn.close();
    }
}

package edu.xust.bigdata.schedual;

public interface Schedual {
    //给调度器添加元素
    void push(String str);
    //从调度器取元素
    String pop();
}

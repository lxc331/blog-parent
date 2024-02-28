package com.example.blog.common;

import java.util.LinkedList;
import java.util.Queue;

// 在某个类中定义静态队列
public class GlobalQueue {
    public static Queue<Object> queue = new LinkedList<>();
}


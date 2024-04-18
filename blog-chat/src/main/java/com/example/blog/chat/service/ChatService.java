package com.example.blog.chat.service;

import com.example.blog.chat.pojo.User;

import java.util.Set;

public interface ChatService {
    User getuid(String username);

    Set<String> onlineUsers(String currentUser);
}

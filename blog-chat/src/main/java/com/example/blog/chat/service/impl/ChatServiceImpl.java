package com.example.blog.chat.service.impl;

import com.example.blog.chat.pojo.User;
import com.example.blog.chat.service.ChatService;
import com.example.blog.chat.service.LoginService;
import com.example.blog.chat.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    LoginService loginService;

    @Override
    public User getuid(String username) {
        User user = new User();
        user.setUid(loginService.getUidByName(username));
        return user;
    }

    @Override
    public Set<String> onlineUsers(String currentUser) {
        ConcurrentHashMap<String, Session> map = WebSocketServer.getSessionPools();
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        Set<String> nameset = new HashSet<String>();
        while (it.hasNext()) {
            String entry = it.next();
            if (!entry.equals(currentUser))
                nameset.add(entry);
        }
        return nameset;
    }
}

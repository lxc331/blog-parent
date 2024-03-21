package com.example.blog.chat.service;

public interface LoginService {

	Long getUidByName(String name);

	String getNameById(long id);
}

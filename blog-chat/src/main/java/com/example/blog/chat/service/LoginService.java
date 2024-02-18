package com.example.blog.chat.service;

public interface LoginService {
	String getPwdByName(String name);

	Long getUidByName(String name);

	String getNameById(long id);
}

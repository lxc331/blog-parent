package com.example.blog.chat.service.impl;



import com.example.blog.chat.mapper.LoginMapper;
import com.example.blog.chat.po.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.example.blog.chat.service.LoginService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	LoginMapper loginmapper;
	
	public String getPwdByName(String name) {
		Staff s=loginmapper.getPwdByName(name);
		if (s != null) {
			return s.getPassword();
		}else {
			return null;
		}
	}
	
	public Long getUidByName(String name) {
		Staff s=loginmapper.getPwdByName(name);

		if (s != null) {
			return (long) s.getStaff_id();
		}else {
			return null;
		}
	}
	
	public String getNameById(long id) {
		Staff s=loginmapper.getNameById(id);
		if(s!=null)
			return s.getUsername();
			else
			return null;
	}
	
	

}

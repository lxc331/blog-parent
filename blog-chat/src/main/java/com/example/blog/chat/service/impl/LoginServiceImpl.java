package com.example.blog.chat.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.blog.chat.mapper.LoginMapper;
import com.example.blog.chat.pojo.SysUser;
import com.example.blog.chat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.example.blog.chat.service.LoginService;

import javax.servlet.http.HttpSession;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	LoginMapper loginmapper;

    @Override
    public String loginValidate(String username, HttpSession httpSession) {
		if (username == null) return "fail";
		else {
			long uid;
			//long uid=loginservice.getUidByName(username);
			LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
			queryWrapper.like(SysUser::getAccount,username);
			SysUser sysUser = loginmapper.selectOne(queryWrapper);

			if (sysUser != null) {
				uid = (long)sysUser.getId();
			}else {
				uid = 0;
			}
			httpSession.setAttribute("uid", uid);
			return "chatroom";
		}
    }

	@Override
	public User currentUser(HttpSession httpSession) {
		Long uid = (Long)httpSession.getAttribute("uid");
		//String name = loginservice.getNameById(uid);
		String name;
		SysUser sysUser = loginmapper.selectById(uid);
		if(sysUser!=null)
			name = sysUser.getAccount();
		else
			name = null;
		return new User(uid, name);
	}

	public Long getUidByName(String name) {
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.like(SysUser::getAccount,name);
		SysUser sysUser = loginmapper.selectOne(queryWrapper);

		if (sysUser != null) {
			return (long) sysUser.getId();
		}else {
			return null;
		}
	}
	
	public String getNameById(long id) {
		SysUser sysUser = loginmapper.selectById(id);
		if(sysUser!=null)
			return sysUser.getAccount();
		else
			return null;
	}
}

package com.example.blog.chat.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.blog.chat.mapper.LoginMapper;
import com.example.blog.chat.pojo.SysUser;
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

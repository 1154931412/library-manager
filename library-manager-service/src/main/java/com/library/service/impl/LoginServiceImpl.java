package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.utils.LibraryResult;
import com.library.mapper.AdminMapper;
import com.library.mapper.ReadCardMapper;
import com.library.pojo.Admin;
import com.library.pojo.AdminExample;
import com.library.pojo.AdminExample.Criteria;
import com.library.pojo.ReadCard;
import com.library.pojo.ReadCardExample;
import com.library.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private ReadCardMapper readCardMapper;

	// 登录检查
	@Override
	public LibraryResult loginCheckAdmin(Integer username, String password) {
		// 创建查询条件
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAdminIdEqualTo(username);
		// 执行查询
		List<Admin> list = adminMapper.selectByExample(example);
		
		// 如果没有此用户名
		if(null == list || list.size() == 0) {
			return LibraryResult.build(400, "用户名或密码错误！");
		}
		
		// 比对密码
		Admin admin = list.get(0);
		if(!(admin.getPassword()).equals(password)) {
			return LibraryResult.build(400, "用户名或密码错误！");
		}
		
		return LibraryResult.build(200, "ok", admin);
	}

	@Override
	public LibraryResult loginCheckReader(Integer username, String password) {
		// 创建查询条件
		ReadCardExample example = new ReadCardExample();
		com.library.pojo.ReadCardExample.Criteria criteria = example.createCriteria();
		criteria.andReaderIdEqualTo(username);
		
		// 执行查询
		List<ReadCard> list = readCardMapper.selectByExample(example);
		
		// 如果没有此用户名
		if(null == list || list.size() == 0) {
			return LibraryResult.build(400, "用户名或密码错误！");
		}
		
		// 比对密码
		ReadCard readCard = list.get(0);
		if(!(readCard.getPasswd()).equals(password)) {
			return LibraryResult.build(400, "用户名或密码错误！");
		}
		
		return LibraryResult.build(250, "ok", readCard);
	}

}

package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.utils.LibraryResult;
import com.library.mapper.AdminMapper;
import com.library.pojo.Admin;
import com.library.pojo.AdminExample;
import com.library.pojo.AdminExample.Criteria;
import com.library.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminMapper adminMapper;

	/**
	 * 管理员修改密码
	 */
	@Override
	public LibraryResult alterAdminPwd(Admin admin, String newPassword) {
		// 创建查询条件
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAdminIdEqualTo(admin.getAdminId());
		criteria.andPasswordEqualTo(admin.getPassword());
		// 执行查询
		List<Admin> list = adminMapper.selectByExample(example);
		if(null != list && list.size() > 0) {
			// 密码，用户名均正确
			// 修改密码
			Admin alterAdmin = new Admin();
			alterAdmin.setAdminId(admin.getAdminId());
			alterAdmin.setPassword(newPassword);
			adminMapper.updateByPrimaryKey(alterAdmin);
			
			return LibraryResult.build(200, "密码修改成功！");
		}
		return LibraryResult.build(400, "原密码错误！");
	}

}

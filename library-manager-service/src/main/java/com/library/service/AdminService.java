package com.library.service;

import com.library.common.utils.LibraryResult;
import com.library.pojo.Admin;

public interface AdminService {
	// 管理员修改密码
	public LibraryResult alterAdminPwd(Admin admin, String newPassword);

}

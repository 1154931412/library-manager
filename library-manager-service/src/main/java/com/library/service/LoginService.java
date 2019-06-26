package com.library.service;

import com.library.common.utils.LibraryResult;

public interface LoginService {
	// 检查是否为管理员
	public LibraryResult loginCheckAdmin(Integer username, String password);
	// 检查是否为读者
	public LibraryResult loginCheckReader(Integer username, String password);
}

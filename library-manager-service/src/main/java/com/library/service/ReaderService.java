package com.library.service;

import java.util.List;

import com.library.common.utils.LibraryResult;
import com.library.pojo.Admin;
import com.library.pojo.LendInfo;
import com.library.pojo.ReaderInfo;

public interface ReaderService {
	// 展示所有读者信息
	public List<ReaderInfo> getAllReaderInfo();
	// 增加读者
	public LibraryResult addReaderInfo(ReaderInfo readerInfo);
	// 显示所有借还日志
	public List<LendInfo> getAllLendLog();
	// 查询读者信息
	public ReaderInfo getReaderInfoById(Integer readId);
	// 更新读者信息
	public LibraryResult updateReaderInfo(ReaderInfo readerInfo);
	// 删除读者信息
	public LibraryResult deleteReader(Integer readId);
	// 删除借阅日志
	public LibraryResult deleteAlog(Long alogId);
}

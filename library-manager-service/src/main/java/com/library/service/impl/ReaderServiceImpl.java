package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.utils.LibraryResult;
import com.library.mapper.LendInfoMapper;
import com.library.mapper.ReadCardMapper;
import com.library.mapper.ReaderInfoMapper;
import com.library.pojo.LendInfo;
import com.library.pojo.LendInfoExample;
import com.library.pojo.ReadCard;
import com.library.pojo.ReadCardExample;
import com.library.pojo.ReaderInfo;
import com.library.pojo.ReaderInfoExample;
import com.library.pojo.ReaderInfoExample.Criteria;
import com.library.service.ReaderService;
@Service
public class ReaderServiceImpl implements ReaderService{
	@Autowired
	private ReaderInfoMapper readerInfoMapper;
	@Autowired
	private	ReadCardMapper readCardMapper;
	@Autowired
	private LendInfoMapper lendInfoMapper;

	/**
	 * 查询所有员工Service
	 */
	@Override
	public List<ReaderInfo> getAllReaderInfo() {
		// 创建查询条件
		ReaderInfoExample example = new ReaderInfoExample();
		List<ReaderInfo> list = readerInfoMapper.selectByExample(example);
		
		return list;
	}
	
	/**
	 * 增加读者
	 */
	@Override
	public LibraryResult addReaderInfo(ReaderInfo readerInfo) {
		// 判断该读者号是否已经存在
		ReaderInfo info = readerInfoMapper.selectByPrimaryKey(readerInfo.getReadId());
		if(info != null) {
			return LibraryResult.build(400, "该读者号已存在！");
		}
		readerInfoMapper.insert(readerInfo);
		// 创建读者信息后，自动添加  读者卡
		// 从读者信息中抽取读者卡所需字段
		ReadCard readCard = new ReadCard();
		readCard.setReaderId(readerInfo.getReadId());
		readCard.setName(readerInfo.getName());
		// 默认卡为激活状态    图书卡状态：0-挂失；1-正常；2-冻结
		readCard.setCardState(1);
		
		// 先将密码设为  11111
		readCard.setPasswd("11111");
		// 将读者卡插入数据库表中
		readCardMapper.insert(readCard);
		// 插入正常
		return LibraryResult.build(200, "读者添加成功！");
	}

	// 打印所有借还日志
	@Override
	public List<LendInfo> getAllLendLog() {
		LendInfoExample example = new LendInfoExample();
		List<LendInfo> list = lendInfoMapper.selectByExample(example);
		return list;
	}
	
	// 通过读者id获取读者信息
	@Override
	public ReaderInfo getReaderInfoById(Integer readId) {
		ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(readId);
		return readerInfo;
	
		
	}
	// 更新读者信息
	@Override
	public LibraryResult updateReaderInfo(ReaderInfo readerInfo) {
		// 更新读者信息表
		ReaderInfoExample example = new ReaderInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andReadIdEqualTo(readerInfo.getReadId());
		readerInfoMapper.updateByExample(readerInfo, example);
		// 更新读者卡
		ReadCard readCard = new ReadCard();
		readCard.setReaderId(readerInfo.getReadId());
		readCard.setName(readerInfo.getName());
		// 默认卡为激活状态    图书卡状态：0-挂失；1-正常；2-冻结
		readCard.setCardState(1);
		// 先将密码设为  11111
		readCard.setPasswd("11111");
		ReadCardExample cardExample = new ReadCardExample();
		com.library.pojo.ReadCardExample.Criteria criteria2 = cardExample.createCriteria();
		criteria2.andReaderIdEqualTo(readCard.getReaderId());
		
		readCardMapper.updateByExample(readCard, cardExample);
		
		return LibraryResult.build(200, "读者更新成功！");
	}

	// 删除读者信息
	@Override
	public LibraryResult deleteReader(Integer readId) {
		// 从读者信息表中删除
		readerInfoMapper.deleteByPrimaryKey(readId);
		// 从读者卡表中删除
		readCardMapper.deleteByPrimaryKey(readId);
		
		return LibraryResult.build(200, "读者删除成功！");
	}

	@Override
	public LibraryResult deleteAlog(Long alogId) {
		// TODO Auto-generated method stub
		lendInfoMapper.deleteByPrimaryKey(alogId);
		return LibraryResult.build(200, "记录删除成功！");
	}

	

}

package com.library.service.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.utils.ExceptionUtil;
import com.library.common.utils.LibraryResult;
import com.library.mapper.BookMapper;
import com.library.mapper.LendInfoMapper;
import com.library.mapper.ReadCardMapper;
import com.library.pojo.Book;
import com.library.pojo.BookExample;
import com.library.pojo.BookExample.Criteria;
import com.library.pojo.LendInfo;
import com.library.pojo.LendInfoExample;
import com.library.pojo.ReadCard;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private LendInfoMapper lendInfoMapper;
	@Autowired
	private ReadCardMapper readCardMapper;

	// 图书搜索
	@Override
	public Book getBookById(Integer bookId) {
		// 添加查询条件
		BookExample example = new BookExample();
		Criteria criteria = example.createCriteria();
		criteria.andBookIdEqualTo(bookId);
		List<Book> list = bookMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// 返回所有图书
	@Override
	public List<Book> getAllBooks() {
		// 添加查询条件
		BookExample example = new BookExample();
		// 查询所有书籍
		List<Book> list = bookMapper.selectByExample(example);
		return list;
	}

	// 增加图书
	@Override
	public LibraryResult addBook(Book book) {
		// 若插入异常，则抛出该异常。
		try {
			bookMapper.insert(book);
		}catch (Exception e) {
			// 打印异常信息
			e.printStackTrace();
			// 将异常信息返回
			return LibraryResult.build(400, ExceptionUtil.getStackTrace(e));
		}

		return LibraryResult.ok();
	}

	// 借阅图书
	@Override
	public LibraryResult lendBook(Integer bookId, Integer lenderId) {
		// 验证该读者是否存在
		ReadCard reader = readCardMapper.selectByPrimaryKey(lenderId);
		if(reader == null ) {
			return LibraryResult.build(400, "该读者不存在");
		}else{
			//查询获得该书
			// 创建查询条件
			BookExample example = new BookExample();
			Criteria criteria = example.createCriteria();
			criteria.andBookIdEqualTo(bookId);
			// 执行查询
			List<Book> bookList = bookMapper.selectByExample(example);
			Book book = bookList.get(0);
			// 准备待更新的图书   // 修改该书状态为0， 图书状态：0-未在馆；1-在馆；
			Book book2 = new Book(book.getBookId(), book.getName(), book.getAuthor(), 
					book.getPublish(), book.getIsbn(), book.getIntroduction(),
					book.getLanguage(), book.getPrice(), book.getPubdate(), 
					book.getClassId(), book.getPressmark(), 0);
			// 更新该图书
			bookMapper.updateByPrimaryKey(book2);
			// 创建借阅日志
			LendInfo lendInfo = new LendInfo();
			// 补全字段；
			lendInfo.setReaderId(lenderId);
			lendInfo.setBookId(bookId);
			lendInfo.setLendDate(new Date());
			// 设置为一个月后归还
			lendInfo.setBackDate((new DateTime(new Date()).plusMonths(1)).toDate());
			// 插入借阅日志
			lendInfoMapper.insert(lendInfo);
			return LibraryResult.build(200, "借阅成功！");
		}
		
	}

	// 归还图书
	@Override
	public LibraryResult rebackBook(Integer bookId) {
		// 修改该书状态为1（在馆）
		//查询获得该书
		// 创建查询条件
		BookExample example = new BookExample();
		Criteria criteria = example.createCriteria();
		criteria.andBookIdEqualTo(bookId);
		// 执行查询
		List<Book> bookList = bookMapper.selectByExample(example);
		Book book = bookList.get(0);
		// 准备待更新的图书   // 修改该书状态为0， 图书状态：0-未在馆；1-在馆；
		Book book2 = new Book(book.getBookId(), book.getName(), book.getAuthor(), 
				book.getPublish(), book.getIsbn(), book.getIntroduction(),
				book.getLanguage(), book.getPrice(), book.getPubdate(), 
				book.getClassId(), book.getPressmark(), 1);
		// 更新该图书
		bookMapper.updateByPrimaryKey(book2);
		// 删除该条借阅日志
		// 创建删除条件
		LendInfoExample example2 = new LendInfoExample();
		com.library.pojo.LendInfoExample.Criteria criteria2 = example2.createCriteria();
		criteria2.andBookIdEqualTo(bookId);
		// 删除
		lendInfoMapper.deleteByExample(example2);
		
		return LibraryResult.build(200, "归还成功！");
	}

	// 删除图书
	@Override
	public LibraryResult deleteBook(Integer bookId) {
		try {
			bookMapper.deleteByPrimaryKey(bookId);
		} catch (Exception e) {
			// TODO: handle exception
			return LibraryResult.build(400, "删除图书失败！");
		}
		
		return LibraryResult.build(200, "删除图书成功！");
	}

	// 编辑图书
	@Override
	public LibraryResult updateBook(Book book) {
		// 创建更新条件
		BookExample example = new BookExample();
		Criteria criteria = example.createCriteria();
		criteria.andBookIdEqualTo(book.getBookId());
		try {
			bookMapper.updateByExample(book, example);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return LibraryResult.build(400, "更新失败！");
		}
		
		return LibraryResult.build(200, "更新成功！");
	}

	// 根据图书名获取图书
	@Override
	public List<Book> getBookByName(String bookName) {
		// TODO Auto-generated method stub
		BookExample example = new BookExample();
		Criteria criteria = example.createCriteria();
		// 根据图书名进行模糊查询
		System.out.println(bookName);
		criteria.andNameLike("%" + bookName + "%");
		
		List<Book> list = bookMapper.selectByExample(example);
		
		return list;
	}


}

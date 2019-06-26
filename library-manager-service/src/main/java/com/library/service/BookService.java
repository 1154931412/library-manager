package com.library.service;

import java.util.List;

import com.library.common.utils.LibraryResult;
import com.library.pojo.Book;
public interface BookService {
	// 查询图书
	public Book getBookById(Integer bookId);
	// 返回所有图书
	public List<Book> getAllBooks();
	// 增加图书
	public LibraryResult addBook(Book book);
	// 借阅图书
	public LibraryResult lendBook(Integer bookId, Integer lenderId);
	// 归还图书
	public LibraryResult rebackBook(Integer bookId);
	// 删除图书
	public LibraryResult deleteBook(Integer bookId);
	// 编辑图书
	public LibraryResult updateBook(Book book);
	// 根据图书名查询图书
	public List<Book> getBookByName(String bookName);

}

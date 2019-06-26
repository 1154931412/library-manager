package com.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.common.utils.LibraryResult;
import com.library.pojo.Book;
import com.library.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	//根据图书id查询图书
	@RequestMapping("/book/{bookId}")
	@ResponseBody
	public Book getBookById(@PathVariable Integer bookId) {
		Book book = bookService.getBookById(bookId);
		return book;

	}

	// 展示所有图书
	@RequestMapping("/allbooks.html")
	@ResponseBody
	public ModelAndView getAllBooks() {
		// 查询所有图书
		List<Book> list = bookService.getAllBooks();
		// 创建一个ModelAndView携带数据并返回逻辑视图
		ModelAndView view = new ModelAndView();
		view.setViewName("admin_books");
		view.addObject("books", list);
		return view;
	}

	// 打开增加图书页面
	@RequestMapping("/book_add.html")
	public String showAddBook() {
		return "admin_book_add";

	}
	
	// 前端提交数据封装为bean后，由于出版日期为String类型，须加上以下结点，
	// 在其映射到Controller时将该字段装换为Date类型，否则将由于bean类型不一致，
	// 导致bean封装失败，产生400错误。
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	// 增加图书book_add_do
	@RequestMapping(value = "/book_add_do.html", method = RequestMethod.POST)
	public String addBook(Book book, RedirectAttributes redirectAttributes) {

		System.out.println(book);
		LibraryResult addBook = bookService.addBook(book);
		/*DateTime dt = new DateTime();*/
		if(addBook.getStatus() == 200) {
			redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            return "redirect:/allbooks.html";
		}else {
			 redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
	         return "redirect:/allbooks.html";
		}

	}
	/**
	 * 必须使用重定向实现，否则会直接返回逻辑视图，会在指定的文件夹目录下寻找返回的该逻辑视图名，
	 * 又因为该逻辑视图名为伪视图（即该jsp页面不真实存在），所以就会出现404错误。
	 * 而重定向，会直接在带有Controller注解的类下扫描，寻找和该逻辑视图名匹配的,
	 * 从而进行映射。
	 */
	/*// 增加图书book_add_do
		@RequestMapping(value = "/book_add_do.html", method = RequestMethod.POST)
		public String addBook(Book book, Model model) {

			System.out.println(book);
			Date pubdate = book.getPubdate();
			LibraryResult addBook = bookService.addBook(book);
			DateTime dt = new DateTime();
			if(addBook.getStatus() == 200) {
				model.addAttribute("succ", "图书添加成功！");
			}else {
				model.addAttribute("succ", "图书添加失败！");
			}
			
			return "allbooks.html";

		}*/
	
	// 映射到借阅图书界面（传统风格拿值）
    @RequestMapping("/lendbook.html")
    public ModelAndView bookLend(HttpServletRequest request){
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.getBookById(bookId);
		ModelAndView modelAndView = new ModelAndView("admin_book_lend");
		modelAndView.addObject("book", book);
		return modelAndView;
    }
	
	// 借阅图书（restful风格）
	@RequestMapping("/lendbookdo.html/{bookId}")
	public String lendBook(RedirectAttributes redirectAttributes,
			Integer readerId,
			@PathVariable Integer bookId) {
		/*Integer bookId = Integer.parseInt(request.getParameter("id"));*/
		LibraryResult result = bookService.lendBook(bookId, readerId);
		if (result.getStatus() == 200) {
			redirectAttributes.addFlashAttribute("succ", result.getMsg());
			return "redirect:/allbooks.html";
		} else {
			redirectAttributes.addFlashAttribute("succ",result.getMsg());
			return "redirect:/allbooks.html";
		}
	}
	
	// 归还图书
	@RequestMapping("returnbook.html/{bookId}")
	public String returnBook(RedirectAttributes redirectAttributes,
			@PathVariable Integer bookId) {
		LibraryResult result = bookService.rebackBook(bookId);
		if(result.getStatus() == 200) {
			redirectAttributes.addFlashAttribute("succ", result.getMsg());
			return "redirect:/allbooks.html";
		}else {
			redirectAttributes.addFlashAttribute("succ", result.getMsg());
			return "redirect:/allbooks.html";
		}
		
	}
	
/*	// 显示图书详情界面  // 使用restful风格会丢失css
	@RequestMapping("/book_detail.html/{bookId}")
	public ModelAndView showBookDetail(@PathVariable Integer bookId) {
		Book book = bookService.getBookById(bookId);
		ModelAndView view = new ModelAndView("admin_book_detail");
		view.addObject("detail", book);
		return view;
	}*/
	
    @RequestMapping("/book_detail.html")
    public ModelAndView bookDetail(HttpServletRequest request){
        Integer bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book = bookService.getBookById(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }
    
    // 更新图书
    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request){
    	Integer bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBookById(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(HttpServletRequest request,Book book,RedirectAttributes redirectAttributes){
        Integer bookId=Integer.parseInt( request.getParameter("id"));
        Book book2=new Book();
        book2.setBookId(bookId);
        book2.setPrice(book.getPrice());
        book2.setState(book.getState());
        book2.setPublish(book.getPublish());
        book2.setPubdate(book.getPubdate());
        book2.setName(book.getName());
        book2.setIsbn(book.getIsbn());
        book2.setClassId(book.getClassId());
        book2.setAuthor(book.getAuthor());
        book2.setIntroduction(book.getIntroduction());
        book2.setPressmark(book.getPressmark());
        book2.setLanguage(book.getLanguage());
        
        LibraryResult result = bookService.updateBook(book2);

        if (result.getStatus() == 200){
            redirectAttributes.addFlashAttribute("succ", result.getMsg());
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", result.getMsg());
            return "redirect:/allbooks.html";
        }
    }
    
    // 删除图书
    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request,RedirectAttributes redirectAttributes){
        Integer bookId=Integer.parseInt(request.getParameter("bookId"));
        LibraryResult result = bookService.deleteBook(bookId);

        if (result.getStatus() == 200){
            redirectAttributes.addFlashAttribute("succ", result.getMsg());
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("error", result.getMsg());
            return "redirect:/allbooks.html";
        }
    }
    
    // 根据图书名模糊查找图书
    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request,String searchWord){
        List<Book> list = bookService.getBookByName(searchWord);
        if(null != list && list.size() > 0) {
        	 ModelAndView modelAndView = new ModelAndView("admin_books");
             modelAndView.addObject("books",list);
             return modelAndView;
             
        } else{
            return new ModelAndView("admin_books","error","没有匹配的图书");
            
        }
       
    }

}

	

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.common.utils.LibraryResult;
import com.library.pojo.LendInfo;
import com.library.pojo.ReaderInfo;
import com.library.service.ReaderService;

@Controller
public class ReaderController {
	@Autowired
	private ReaderService readerService;
	
	
	// 前端提交数据封装为bean后，由于出版日期为String类型，须加上以下结点，
	// 在其映射到Controller时将该字段装换为Date类型，否则将由于bean类型不一致，
	// 导致bean封装失败，产生400错误。
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	/**
	 * 展示所有读者信息
	 */
	@RequestMapping("/allreaders.html")
	public String getAllReaderInfo(Model model) {
		System.out.println("展示所有读者信息");
		List<ReaderInfo> list = readerService.getAllReaderInfo();
		model.addAttribute("readers", list);
		return "admin_readers";
		
	}
	
	/**
	 * 跳转到增加读者页面
	 */
	@RequestMapping("reader_add.html")
	public String showReaderAdd() {
		return "admin_reader_add";
	}
	
	/**
	 * 增加读者信息
	 * 
	 */
	@RequestMapping(value = "/reader_add_do.html", method = RequestMethod.POST)
	public String addBook(ReaderInfo readerInfo, RedirectAttributes redirectAttributes) {

		System.out.println(readerInfo);
		LibraryResult addReaderInfo = readerService.addReaderInfo(readerInfo);
		if(addReaderInfo.getStatus() == 200) {
			redirectAttributes.addFlashAttribute("succ", addReaderInfo.getMsg());
			return "redirect:/allreaders.html";
		}else {
			 redirectAttributes.addFlashAttribute("succ", addReaderInfo.getMsg());
	         return "redirect:/allreaders.html";
		}

	}
	
	/**
	 * 显示全部借还日志
	 */
	@RequestMapping("/lendlist.html")
	public String showLengLog(Model model){
		List<LendInfo> list = readerService.getAllLendLog();
		model.addAttribute("list", list);
		return "admin_lend_list";
	}
	
	// 删除借还日志
	@RequestMapping("/deleteAlog.html/{alogId}")
	public String delegeAlog(RedirectAttributes redirectAttributes,
			@PathVariable Long alogId){
		LibraryResult result = readerService.deleteAlog(alogId);
		if(result.getStatus() == 200) {
			redirectAttributes.addFlashAttribute("succ", result.getMsg());
            return "redirect:/lendlist.html";
		}else {
			redirectAttributes.addFlashAttribute("succ", "记录删除失败！");
            return "redirect:/lendlist.html";
		}
		
	}
	
	// 展示读者信息修改页面
	  @RequestMapping("/reader_edit.html")
	    public ModelAndView readerInfoEdit(HttpServletRequest request){
		    Integer readerId= Integer.parseInt(request.getParameter("readerId"));
	        ReaderInfo readerInfo=readerService.getReaderInfoById(readerId);
	        ModelAndView modelAndView=new ModelAndView("admin_reader_edit");
	        modelAndView.addObject("readerInfo",readerInfo);
	        return modelAndView;
	    }

	    @RequestMapping("/reader_edit_do.html")
	    public String readerInfoEditDo(HttpServletRequest request,
	    		ReaderInfo readerInfo,
	    		RedirectAttributes redirectAttributes){
	        Integer readerId= Integer.parseInt(request.getParameter("id"));
	        readerInfo.setReadId(readerId);
	        LibraryResult result = readerService.updateReaderInfo(readerInfo);
	        if(result.getStatus() == 200) {
	        	redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
	            return "redirect:/allreaders.html";
	        }else {
	        	redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
                return "redirect:/allreaders.html";
	        }  

	    }
	    
	    // 删除读者信息
	    @RequestMapping("reader_delete.html")
	    public String readerDelete(HttpServletRequest request,RedirectAttributes redirectAttributes){
	        Integer readerId= Integer.parseInt(request.getParameter("readerId"));
	        LibraryResult result = readerService.deleteReader(readerId);

	        if(result.getStatus() == 200){
	            redirectAttributes.addFlashAttribute("succ", result.getMsg());
	            return "redirect:/allreaders.html";
	        }else {
	            redirectAttributes.addFlashAttribute("error", "删除失败！");
	            return "redirect:/allreaders.html";
	        }

	    }

}

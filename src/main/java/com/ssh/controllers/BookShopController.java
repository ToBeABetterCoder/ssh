package com.ssh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssh.entities.Book;
import com.ssh.exception.BookStockException;
import com.ssh.exception.UserAccountException;
import com.ssh.service.BookShopService;

@Controller
@RequestMapping("/bookShop")
public class BookShopController {
	
	@Autowired
	private BookShopService bookShopService;
	
	@RequestMapping("/list")
	public ModelAndView displayBooks(){
		List<Book> books = bookShopService.getAllBooks();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("books", books);
		return modelAndView;
	}

	@RequestMapping("/purchase")
	public ModelAndView purchase(@RequestParam("isbn") String isbn, @RequestParam("username") String username){
		String message = "";
		try {
			bookShopService.purchase(isbn, username);
		} catch (BookStockException e) {
			message = e.getMessage();
		} catch (UserAccountException e) {
			message = e.getMessage();
		}
		ModelAndView modelAndView = new ModelAndView();
		if(message.equals("")){
			modelAndView.setViewName("success");
			modelAndView.addObject("message", "¸¶¿î³É¹¦");
		} else {
			modelAndView.setViewName("error");
			modelAndView.addObject("message", message);
		}
		return modelAndView;
	}
}

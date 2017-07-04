package com.ssh.service;

import java.util.List;

import com.ssh.entities.Book;
import com.ssh.exception.BookStockException;
import com.ssh.exception.UserAccountException;

public interface BookShopService {
	
	List<Book> getAllBooks();

	void purchase(String isbn, String username) throws BookStockException, UserAccountException;
}

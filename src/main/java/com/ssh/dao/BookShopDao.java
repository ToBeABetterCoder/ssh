package com.ssh.dao;

import java.util.List;

import com.ssh.entities.Book;
import com.ssh.exception.BookStockException;
import com.ssh.exception.UserAccountException;

public interface BookShopDao {
	
	List<Book> getAllBooks();

	int findBookPriceByIsbn(String isbn);
	
	void updateBookStock(String isbn) throws BookStockException;
	
	void updateUserAccount(String username, int price) throws UserAccountException;
}

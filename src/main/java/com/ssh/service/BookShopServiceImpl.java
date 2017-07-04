package com.ssh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.BookShopDao;
import com.ssh.entities.Book;
import com.ssh.exception.BookStockException;
import com.ssh.exception.UserAccountException;

@Service
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;
	
	@Transactional
	@Override
	public void purchase(String isbn, String username) throws BookStockException, UserAccountException {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Book> getAllBooks() {
		return bookShopDao.getAllBooks();
	}

}

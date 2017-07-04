package com.ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.entities.Book;
import com.ssh.exception.BookStockException;
import com.ssh.exception.UserAccountException;

@Repository
public class BookShopDaoImpl implements BookShopDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int findBookPriceByIsbn(String isbn) {
		return (Integer)getSession().createQuery("SELECT price FROM Book WHERE isbn=?").setString(0, isbn).uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) throws BookStockException {
		int stock = (Integer)getSession().createQuery("SELECT stock FROM Book WHERE isbn=?").setString(0, isbn).uniqueResult();
		if(stock == 0)
			throw new BookStockException("¿â´æ²»×ã");
		getSession().createQuery("UPDATE Book SET stock=stock-1 WHERE isbn=?").setString(0, isbn).executeUpdate();
	}

	@Override
	public void updateUserAccount(String username, int price) throws UserAccountException {
		int balance = (Integer)getSession().createQuery("SELECT balance FROM Account WHERE username=?").setString(0, username).uniqueResult();
		if(balance < price)
			throw new UserAccountException("Óà¶î²»×ã");
		getSession().createQuery("UPDATE Account SET balance=balance-? WHERE username=?").setInteger(0, price).setString(1, username).executeUpdate();
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = getSession().createQuery("FROM Book").list();
		return books;
	}

}

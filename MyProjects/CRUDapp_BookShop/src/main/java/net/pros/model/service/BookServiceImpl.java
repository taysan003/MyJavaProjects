package net.pros.model.service;

import net.pros.model.Book;

import java.util.List;

import net.pros.model.dao.BookDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDao.remuveBook(id);
    }

    @Override
    @Transactional
    public Book getBookById(int id)
    {
        return this.bookDao.getBookByID(id);
    }

    @Override
    @Transactional
    public List<Book> listBooks()
    {
        return this.bookDao.listBooks();
    }
}

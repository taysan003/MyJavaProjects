package net.pros.model.dao;

import net.pros.model.Book;

import java.util.List;

/**
 * Created by Andrei on 08-Jul-16.
 */
public interface BookDao {
    public void addBook(Book book);
    public void updateBook (Book book);
    public void remuveBook(int id);
    public Book getBookByID(int id);
    public List<Book>listBooks();
}

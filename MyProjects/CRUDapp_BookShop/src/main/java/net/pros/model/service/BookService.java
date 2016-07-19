package net.pros.model.service;

import net.pros.model.Book;

import java.util.List;

/**
 * Created by Andrei on 08-Jul-16.
 */
public interface BookService {
    public void addBook (Book book);
    public void updateBook (Book book);
    public void removeBook (int id);
    public Book getBookById (int id);
    public List<Book> listBooks();
}

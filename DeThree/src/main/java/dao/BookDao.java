package dao;

import entity.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {
    List<Book> listRatedBooks(String author, int rating);
    Map<String, Long> countBooksByAuthor();
}

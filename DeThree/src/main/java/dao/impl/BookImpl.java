package dao.impl;

import dao.BookDao;
import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookImpl implements BookDao {
    private EntityManager em;

    public BookImpl() {
        em = Persistence
                .createEntityManagerFactory("mariadb")
                .createEntityManager();
    }
    @Override
    public List<Book> listRatedBooks(String author, int rating) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.ISBN FROM books b JOIN reviews r ON b.ISBN = r.ISBN JOIN books_authors ba ON ba.ISBN = b.ISBN WHERE ba.author = ? AND r.rating >= ?";

       List<String> ma = em.createNativeQuery(sql)
                .setParameter(1, author)
                .setParameter(2, rating)
                .getResultList();
        for (String isbn : ma) {
            Book book = em.find(Book.class, isbn);
            books.add(book);
        }
        return books;
    }

    @Override
    public Map<String, Long> countBooksByAuthor() {
        Map<String, Long> map = new HashMap<>();
        String sql = "SELECT ba.author, COUNT(*) FROM books b \n" +
                "INNER JOIN book_translations bt ON b.ISBN = bt.ISBN\n" +
                "INNER JOIN  books_authors ba ON ba.ISBN = b.ISBN\n" +
                "GROUP BY ba.author ORDER BY  ba.author desc";
        List<?> list = em.createNativeQuery(sql).getResultList();
        list.stream()
                .map(c -> (Object[]) c)
                .forEach(a -> {
                    map.put((String) a[0], (Long) a[1]);
                });
        return map;
    }
}

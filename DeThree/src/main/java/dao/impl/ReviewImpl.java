package dao.impl;

import dao.ReviewsDao;
import entity.Book;
import entity.Person;
import entity.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ReviewImpl implements ReviewsDao {
    private EntityManager em;

    public ReviewImpl() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }
    @Override
    public boolean updateReviews(String isbn, String readerId, int rateing, String comment) {
        if(em.find(Book.class, isbn) == null || em.find(Person.class, readerId) == null ) {
            System.out.println("Book or reader not found");
            return false;
        }
        if(rateing < 1 || rateing > 5 || comment.length() < 1) {
            return false;
        }
        Reviews reviews = new Reviews(comment, rateing, em.find(Book.class, isbn), em.find(Person.class, readerId));
        try {
            em.getTransaction().begin();
            em.merge(reviews);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }
}

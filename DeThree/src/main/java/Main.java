import dao.BookDao;
import dao.ReviewsDao;
import dao.impl.BookImpl;
import dao.impl.ReviewImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");
//        EntityManager em = emf.createEntityManager();
//
//        BookDao bookDao = new BookImpl();
//        bookDao.listRatedBooks("David Thomas", 5).forEach(System.out::println);
//
//        bookDao.countBooksByAuthor().forEach((k, v) -> System.out.println(k + " : " + v));

        ReviewsDao reviewsDao = new ReviewImpl();
        if(reviewsDao.updateReviews("888-0132350800", "11", 1, "Great book!")) {
            System.out.println("Review update successfully");
        } else {
            System.out.println("Failed to update review");
        }

    }
}

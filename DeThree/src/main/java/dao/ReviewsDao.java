package dao;

public interface ReviewsDao {
    boolean updateReviews(String isbn, String readerId, int rateing, String comment);
}

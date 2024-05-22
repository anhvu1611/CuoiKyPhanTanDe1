import dao.FoodDao;
import dao.ItemDao;
import dao.impl.FoodImpl;
import dao.impl.ItemImpl;
import entity.Food;
import enums.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlserver");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        FoodDao foodDao = new FoodImpl();
//        Type type = Type.MAIN_COURSE;
//        foodDao.addFood(new Food("F161", type, 10, 10));
        ItemDao itemDao = new ItemImpl();
        itemDao.listItems("Bob's Produce").forEach(System.out::println);


        foodDao.listFoodAndCost().forEach((food, cost) -> {
            System.out.println(food + " " + cost);
        });
    }
}

import dao.ItemDao;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Item;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import dao.FoodService;
import dao.impl.FoodImpl;

import java.util.List;

import static enums.Type.APPETIZER;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            // Add your code here
//            tx.commit();
//        } catch (Exception e) {
//            emf.close();
//            e.printStackTrace();
//        }

        FoodService foodService = new FoodImpl();
//        foodService.addFood(new Food("F161",APPETIZER, 10, 20000));
        ItemDao itemDao = new ItemImpl();
        itemDao.listItems("food").forEach(System.out::println);

        foodService.listFoodAndCost().forEach((k,v) -> System.out.println(k + " " + v));
    }
}

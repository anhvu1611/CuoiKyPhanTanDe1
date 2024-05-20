package dao.impl;

import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import dao.FoodService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodImpl implements FoodService {
    private EntityManager em;

    public FoodImpl() {
        em = Persistence.createEntityManagerFactory("mariadb")
                .createEntityManager();
    }
    @Override
    public boolean addFood(Food food) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(food);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
        }
        return false;
    }

    @Override
    public Map<Food, Double> listFoodAndCost() {
        Map<Food, Double> foodAndCost = new HashMap<>();

        String query = "SELECT f.id , (SUM( ingre.price * ingre.quantity) + ( f.serving_time + f.preparation_time ) * 0.2) AS gia FROM ingredients ingre\n" +
                "JOIN items_ingredients iteinge ON ingre.ingredient_id = iteinge.ingredient_id\n" +
                "JOIN items i ON i.id = iteinge.item_id\n" +
                "JOIN foods f ON f.id = i.id\n" +
                "GROUP BY f.id";
        List<?> foods = em.createNativeQuery(query).getResultList();
//        for (Object food : foods) {
//            Object[] f = (Object[]) food;
//            foodAndCost.put(em.find(Food.class, f[0]), (Double) f[1]);
//        }
        foods.stream()
                .map(food -> (Object[]) food)
                .forEach(f -> foodAndCost
                        .put(em.find(Food.class, f[0]), (Double) f[1]));
        return foodAndCost;
    }

}

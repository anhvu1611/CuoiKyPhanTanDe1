package dao.impl;

import dao.FoodDao;
import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodImpl implements FoodDao {
    private EntityManager em;
    public FoodImpl() {
        em = Persistence
                .createEntityManagerFactory("sqlserver")
                .createEntityManager();
    }
    @Override
    public boolean addFood(Food food) {
        EntityTransaction tx = em.getTransaction();
        if (!food.getId().matches("F\\d{3,}")) {
            return false;
        }
        try {
            tx.begin();
            em.persist(food);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        }
    }

    @Override
    public Map<Food, Double> listFoodAndCost() {
        Map<Food, Double> map = new LinkedHashMap<>();
        String sql = "select f.id, (sum(ir.price * ir.quantity) + (f.serving_time +f.preparation_time)*0.2) as gia  from items i \n" +
                "inner join items_ingredients ii on ii.item_id = i.id\n" +
                "inner join ingredients ir on ir.ingredient_id = ii.ingredient_id\n" +
                "inner join foods f on f.id = i.id\n" +
                "group by f.id, f.serving_time ,f.preparation_time order by gia desc";
        List<?> list = em.createNativeQuery(sql).getResultList();
        list.stream()
                .map(o -> (Object[]) o)
                .forEach(objects -> {
                    Food food = em.find(Food.class, objects[0]);
                    map.put(food, (Double) objects[1]);
                });
        return map;
    }
}

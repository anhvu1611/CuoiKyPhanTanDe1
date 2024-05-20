package dao;

import entity.Food;

import java.util.Map;

public interface FoodService {
    boolean addFood(Food food);
    Map<Food, Double> listFoodAndCost();
}

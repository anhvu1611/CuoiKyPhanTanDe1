import dao.FoodService;
import dao.impl.FoodImpl;
import entity.Food;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static enums.Type.APPETIZER;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestFood {
    @BeforeAll
    void setUp() {
        System.out.println("Before all tests");
    }


    @Test
    void testAddFood() {
        FoodService foodService = new FoodImpl();
//        assertTrue(foodService.addFood(new Food("F166", APPETIZER, 10, 20000)));
        assertFalse(foodService.addFood(new Food("F166", APPETIZER, 10, 20000)));
        assertFalse(foodService.addFood(new Food("166F", APPETIZER, 10, 20000)));
    }


    @AfterAll
    void tearDown() {
        System.out.println("After all tests");
    }
}
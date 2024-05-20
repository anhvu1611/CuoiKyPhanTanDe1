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
    void testAddFood() throws Exception{
        FoodService foodService = new FoodImpl();
        assertTrue(foodService.addFood(new Food("F167", APPETIZER, 10, 20000)));
    }

    @Test
    void testDoubleAddFood() throws Exception{
        FoodService foodService = new FoodImpl();
        assertTrue(foodService.addFood(new Food("F168", APPETIZER, 10, 20000)));
        assertEquals(false,foodService.addFood(new Food("F168", APPETIZER, 10, 20000)));
    }

    @Test
    void testInvalid() throws Exception {
        FoodService foodService = new FoodImpl();
        assertFalse(foodService.addFood(new Food("A", APPETIZER, 10, 20000)));
    }


    @AfterAll
    void tearDown() {
        System.out.println("After all tests");
    }
}
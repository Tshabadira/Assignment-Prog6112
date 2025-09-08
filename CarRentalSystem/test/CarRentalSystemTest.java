import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CarRentalSystemTest {
    
    private Car car;
    private Customer customer;
    
    @BeforeEach
    void setUp() {
        car = new Car("Toyota", "Corolla", 500.0);
        customer = new Customer("John Doe");
    }
    
    @Test
    @DisplayName("Test Car creation and initial state")
    void testCarCreation() {
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals(500.0, car.getPricePerDay());
        assertTrue(car.isAvailable());
        assertEquals(0, car.getReturnDay());
    }
    
    @Test
    @DisplayName("Test car availability can be set")
    void testCarAvailability() {
        assertTrue(car.isAvailable());
        
        car.setAvailable(false);
        assertFalse(car.isAvailable());
        
        car.setAvailable(true);
        assertTrue(car.isAvailable());
    }
    
    @Test
    @DisplayName("Test car return day can be set")
    void testCarReturnDay() {
        assertEquals(0, car.getReturnDay());
        
        car.setReturnDay(5);
        assertEquals(5, car.getReturnDay());
    }
    
    @Test
    @DisplayName("Test customer creation")
    void testCustomerCreation() {
        assertEquals("John Doe", customer.getName());
        assertNull(customer.getRentedCar());
        assertEquals(0, customer.getRentalDays());
        assertEquals(0, customer.getReturnDay());
        assertEquals(0.0, customer.getTotalCost());
    }
    
    @Test
    @DisplayName("Test customer can rent a car")
    void testRentCar() {
        int currentDay = 1;
        int rentalDays = 3;
        
        customer.rentCar(car, rentalDays, currentDay);
        
        // Check customer state
        assertEquals(car, customer.getRentedCar());
        assertEquals(rentalDays, customer.getRentalDays());
        assertEquals(currentDay + rentalDays, customer.getReturnDay());
        assertEquals(500.0 * rentalDays, customer.getTotalCost());
        
        // Check car state
        assertFalse(car.isAvailable());
        assertEquals(currentDay + rentalDays, car.getReturnDay());
    }
    
    @Test
    @DisplayName("Test customer can return a car")
    void testReturnCar() {
        // First rent a car
        customer.rentCar(car, 3, 1);
        assertFalse(car.isAvailable());
        assertNotNull(customer.getRentedCar());
        
        // Then return it
        customer.returnCar();
        
        // Check customer state after return
        assertNull(customer.getRentedCar());
        assertEquals(0, customer.getRentalDays());
        assertEquals(0, customer.getReturnDay());
        assertEquals(0.0, customer.getTotalCost());
        
        // Check car state after return
        assertTrue(car.isAvailable());
        assertEquals(0, car.getReturnDay());
    }
    
    @Test
    @DisplayName("Test return car when no car is rented")
    void testReturnCarWhenNoneRented() {
        // Customer has no rented car initially
        assertNull(customer.getRentedCar());
        
        // Should not throw exception
        assertDoesNotThrow(() -> customer.returnCar());
        
        // State should remain unchanged
        assertNull(customer.getRentedCar());
        assertEquals(0, customer.getTotalCost());
    }
    
    @Test
    @DisplayName("Test total cost calculation for different scenarios")
    void testTotalCostCalculation() {
        // No car rented
        assertEquals(0.0, customer.getTotalCost());
        
        // Rent expensive car for multiple days
        Car expensiveCar = new Car("BMW", "X5", 1500.0);
        customer.rentCar(expensiveCar, 5, 1);
        assertEquals(7500.0, customer.getTotalCost());
        
        // Return and rent different car
        customer.returnCar();
        Car cheapCar = new Car("Ford", "Fiesta", 400.0);
        customer.rentCar(cheapCar, 2, 6);
        assertEquals(800.0, customer.getTotalCost());
    }
    
    @Test
    @DisplayName("Test multiple customers with same car")
    void testMultipleCustomersScenario() {
        Customer customer1 = new Customer("Alice");
        Customer customer2 = new Customer("Bob");
        
        // Customer1 rents the car
        customer1.rentCar(car, 3, 1);
        assertFalse(car.isAvailable());
        assertEquals(car, customer1.getRentedCar());
        
        // Customer2 tries to rent the same car (in real system this would be prevented)
        customer2.rentCar(car, 2, 1);
        assertEquals(car, customer2.getRentedCar());
        
        // Car return day should be updated to customer2's return day
        assertEquals(3, car.getReturnDay());
    }
    
    @Test
    @DisplayName("Test vehicle inheritance through Car class")
    void testVehicleInheritance() {
        // Car should inherit all Vehicle properties and methods
        assertTrue(car instanceof Vehicle);
        
        // Test that all Vehicle methods work through Car
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals(500.0, car.getPricePerDay());
        assertTrue(car.isAvailable());
        
        car.setAvailable(false);
        assertFalse(car.isAvailable());
        
        car.setReturnDay(10);
        assertEquals(10, car.getReturnDay());
    }
    
    @Test
    @DisplayName("Test edge case: zero days rental")
    void testZeroDaysRental() {
        customer.rentCar(car, 0, 5);
        
        assertEquals(0, customer.getRentalDays());
        assertEquals(5, customer.getReturnDay()); // currentDay + 0 days
        assertEquals(0.0, customer.getTotalCost()); // 500 * 0 = 0
    }
    
    @Test
    @DisplayName("Test edge case: negative price per day")
    void testNegativePricePerDay() {
        Car freeCar = new Car("Test", "Free", -100.0);
        customer.rentCar(freeCar, 3, 1);
        
        assertEquals(-300.0, customer.getTotalCost());
    }
}
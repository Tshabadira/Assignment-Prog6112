import java.util.*;

public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Array of cars
        Car[] cars = {
            new Car("Toyota", "Corolla", 500),
            new Car("BMW", "X5", 1500),
            new Car("Mercedes", "C200", 1200),
            new Car("Ford", "Fiesta", 400),
            new Car("Audi", "Tfsi", 600),
            new Car("Volkswagen","GTI",800),
            new Car("Hyundai","I20",500),
            new Car("Toyota","Land Cruiser",750),
            new Car("Bmw","1 Series",650),
        };

        // List of customers
        ArrayList<Customer> customers = new ArrayList<>();

        int currentDay = 1;
        String action;

        do {
            System.out.println("\n===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. View Reports");
            
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            action = sc.nextLine();

            switch (action) {
                case "1": // Rent a car
                    System.out.print("\nEnter your name: ");
                    String customerName = sc.nextLine();
                    Customer customer = new Customer(customerName);

                    // Show available cars
                    System.out.println("\nAvailable Cars:");
                    boolean hasAvailable = false;
                    for (int i = 0; i < cars.length; i++) {
                        if (cars[i].isAvailable()) {
                            hasAvailable = true;
                            System.out.println((i + 1) + ". " + cars[i].getBrand() + " " + cars[i].getModel() +
                                               " - R" + cars[i].getPricePerDay() + " per day");
                        }
                    }

                    if (!hasAvailable) {
                        System.out.println("No cars available right now!");
                        break;
                    }

                    // Choose car
                    System.out.print("\nChoose a car by number: ");
                    int choice = sc.nextInt();
                    System.out.print("Enter number of days: ");
                    int days = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    if (!cars[choice - 1].isAvailable()) {
                        System.out.println("Sorry, that car is not available!");
                        break;
                    }

                    Car selectedCar = cars[choice - 1];
                    customer.rentCar(selectedCar, days, currentDay);
                    customers.add(customer);
                    System.out.println("Car rented successfully!");
                    break;

                case "2": // Return a car
                    System.out.print("\nEnter your name: ");
                    String returnName = sc.nextLine();
                    boolean found = false;
                    for (Customer c : customers) {
                        if (c.getName().equalsIgnoreCase(returnName) && c.getRentedCar() != null) {
                            c.returnCar();
                            System.out.println("Car returned successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("No active rental found for this customer.");
                    }
                    break;

                case "3": // View reports
                    System.out.println("\n===== Rental Reports =====");
                    for (Customer c : customers) {
                        c.printReport();
                    }

                    System.out.println("\nUnavailable Cars:");
                    for (Car car : cars) {
                        if (!car.isAvailable()) {
                            System.out.println(car.getBrand() + " " + car.getModel() +
                                               " - Will return on Day " + car.getReturnDay());
                        }
                    }

                    System.out.println("\nAvailable Cars:");
                    for (Car car : cars) {
                        if (car.isAvailable()) {
                            System.out.println(car.getBrand() + " " + car.getModel());
                        }
                    }
                    break;

                case "4": // Exit
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (!action.equals("4"));

        sc.close();
    }
}
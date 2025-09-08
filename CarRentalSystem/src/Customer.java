public class Customer {
    private String name;
    private Car rentedCar;
    private int rentalDays;
    private int returnDay;

    public Customer(String name) {
        this.name = name;
    }

    public void rentCar(Car car, int days, int currentDay) {
        this.rentedCar = car;
        this.rentalDays = days;
        this.returnDay = currentDay + days;

        car.setAvailable(false);
        car.setReturnDay(this.returnDay);
    }

    public void returnCar() {
        if (rentedCar != null) {
            rentedCar.setAvailable(true);
            rentedCar.setReturnDay(0); // reset
            rentedCar = null;
            rentalDays = 0;
            returnDay = 0;
        }
    }

    public String getName() { return name; }
    public Car getRentedCar() { return rentedCar; }
    public int getRentalDays() { return rentalDays; }
    public int getReturnDay() { return returnDay; }

    public double getTotalCost() {
        if (rentedCar != null) {
            return rentedCar.getPricePerDay() * rentalDays;
        }
        return 0;
    }

    public void printReport() {
        System.out.println("Customer: " + name);
        if (rentedCar != null) {
            System.out.println("Car: " + rentedCar.getBrand() + " " + rentedCar.getModel());
            System.out.println("Days: " + rentalDays);
            System.out.println("Return Day: Day " + returnDay);
            System.out.println("Total Cost: R" + getTotalCost());
        } else {
            System.out.println("No active rental.");
        }
        System.out.println("-----------------------------");
    }
}
public class Vehicle {
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean available = true;
    private int returnDay;

    public Vehicle(String brand, String model, double pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public int getReturnDay() { return returnDay; }
    public void setReturnDay(int returnDay) { this.returnDay = returnDay; }
}
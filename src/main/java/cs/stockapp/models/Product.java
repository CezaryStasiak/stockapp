package cs.stockapp.models;

public final class Product {
    private final int id;
    private final String name;
    private final double price;
    private final int amount;
    private final double totalPrice;

    public Product(int id, String name, double price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        totalPrice = amount * price;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalPrice(){
        return totalPrice;
    }
}
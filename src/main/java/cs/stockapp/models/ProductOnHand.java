package cs.stockapp.models;

public final class ProductOnHand {
    private final int id;
    private final String name;
    private final double price;
    private final String unit;
    private final float amount;
    private final double totalPrice;

    public ProductOnHand(int id, String name, double price, String unit, float amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
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

    public float getAmount() {
        return amount;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public String getUnit() {
        return unit;
    }
}

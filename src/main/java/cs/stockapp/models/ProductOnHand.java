package cs.stockapp.models;

public final class ProductOnHand extends Product {
    private final float amount;
    private final double totalPrice;

    public ProductOnHand(int id, String name, double price, String unit, float amount) {
        super(id, name, price, unit);
        this.amount = amount;
        totalPrice = amount * price;
    }

    public float getAmount() {
        return amount;
    }

    public double getTotalPrice(){
        return totalPrice;
    }
}

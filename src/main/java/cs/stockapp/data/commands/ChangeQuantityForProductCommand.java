package cs.stockapp.data.commands;

public final class ChangeQuantityForProductCommand {
    private int productId;
    private double quantity;

    public ChangeQuantityForProductCommand(int productId, double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }
}

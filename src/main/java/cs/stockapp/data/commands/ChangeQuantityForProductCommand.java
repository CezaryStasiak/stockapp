package cs.stockapp.data.commands;

public final class ChangeQuantityForProductCommand {
    private final int productId;
    private final int userId;
    private final double quantity;

    public ChangeQuantityForProductCommand(int productId, int userId, double quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

    public double getQuantity() {
        return quantity;
    }
}

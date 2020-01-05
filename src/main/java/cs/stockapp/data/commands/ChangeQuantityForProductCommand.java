package cs.stockapp.data.commands;

public final class ChangeQuantityForProductCommand {
    private int productId;
    private int userId;
    private double quantity;

    public ChangeQuantityForProductCommand(int productId, double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void setUserId(int userId){
        this.userId = userId;
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

package cs.stockapp.models;

public class User {
    private int id;
    private String name;
    private String password;
    private String created_date;
    private int shop_id;

    public User(String name, String password, int shop_id) {
        this.name = name;
        this.password = password;
        this.shop_id = shop_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreated_date() {
        return created_date;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}

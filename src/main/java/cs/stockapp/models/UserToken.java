package cs.stockapp.models;

public class UserToken {
    private int id;
    private String token;

    public UserToken(int id, String token){
        this.id = id;
        this.token = token;
    }


    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}

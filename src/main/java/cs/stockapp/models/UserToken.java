package cs.stockapp.models;

public final class UserToken {
    private final int id;
    private final String token;

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

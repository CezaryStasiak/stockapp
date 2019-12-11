package cs.stockapp.factory;

import cs.stockapp.models.UserToken;

public final class TokenFactory {
    public static UserToken getUserToken(int userId, String tokenValue){
        return new UserToken(userId, tokenValue);
    }
}

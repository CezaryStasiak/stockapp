package cs.stockapp.service;

import cs.stockapp.models.UserToken;

public interface UserTokenService {
    void addUserTokenOrUpdateExisting(UserToken userToken);

    void deleteTokenWithId(int id);

    int getUserId(String token);
}

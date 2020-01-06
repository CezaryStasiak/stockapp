package cs.stockapp.service;

import cs.stockapp.models.UserToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class UserTokenServiceImpl implements UserTokenService{

    private final List<UserToken> tokens;

    private UserTokenServiceImpl(){
        tokens = new ArrayList<UserToken>();
    }

    public void addUserTokenOrUpdateExisting(UserToken userToken){
        if (tryUpdate(userToken) == false){
            tokens.add(userToken);
        }
    }

    private boolean tryUpdate(UserToken userToken){
        for (UserToken token : tokens){
            if (token.getId() == userToken.getId()) {
                token = userToken;
                return true;
            }
        }
        return false;
    }


    public void deleteTokenWithId(int id){
        UserToken toDelete = null;

        for (UserToken userToken : tokens){
            if (userToken.getId() == id){
                toDelete = userToken;
                break;
            }
        }

        tokens.remove(toDelete);
    }

    public int getUserId(String token){
        for (UserToken userToken : tokens){
            if (userToken.getToken().equals(token)){
                return userToken.getId();
            }
        }
        return -1;
    }

}

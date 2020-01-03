package cs.stockapp.service;

import cs.stockapp.data.repositories.UserRepositoryImpl;
import cs.stockapp.factory.CookieFactory;
import cs.stockapp.models.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionServiceImpl implements SessionService {

    private final UserTokenService userTokenService;
    private final UserRepositoryImpl userRepository;

    @Autowired
    public SessionServiceImpl(UserTokenService userTokenService, UserRepositoryImpl userRepository){
        this.userTokenService = userTokenService;
        this.userRepository = userRepository;
    }

    @Override
    public int getUserIdIfIsAuthenticated(HttpServletRequest request) {
        int id = -1;
        for (Cookie cookie : request.getCookies()){
            if (cookie.getName().equals(CookieFactory.LOGIN_COOKIE_NAME)){
                id = userTokenService.getUserId(cookie.getValue());
            }
        }
        return id;
    }

    @Override
    public boolean loginUser(String userName, String password, HttpServletResponse response){

        int userId = userRepository.getUserId(userName, password);

        if (userId != -1){
                String hash = userName + password;
                UserToken userToken = new UserToken(userId, Integer.toString(hash.hashCode()));
                userTokenService.addUserTokenOrUpdateExisting(userToken);
                Cookie loginCookie = CookieFactory.getLoginCookie(userToken.getToken(), 60*30);
                response.addCookie(loginCookie);
                return true;
        } else {
            return false;
        }
    }
    @Override
    public void logoutUser(HttpServletResponse response, int id){

        try {
            response.addCookie(CookieFactory.getLoginCookie("", 0));
            userTokenService.deleteTokenWithId(id);
        } catch (Exception e){
            return;
        }

    }

}

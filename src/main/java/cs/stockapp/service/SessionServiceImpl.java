package cs.stockapp.service;

import cs.stockapp.factory.CookieFactory;
import cs.stockapp.factory.TokenFactory;
import cs.stockapp.models.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SessionServiceImpl implements SessionService {

    private UserTokenService userTokenService;

    @Autowired
    public SessionServiceImpl(UserTokenService userTokenService){
        this.userTokenService = userTokenService;
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
    public boolean loginUser(String userName, String password, HttpServletResponse response) {
        int id = 15;
        String name = "admin";
        String pass = "admin";



        if (userName.equals(name) && password.equals(pass)){
            try {
                String hash = name + pass;
                UserToken userToken = TokenFactory.getUserToken(id, Integer.toString(hash.hashCode()));
                userTokenService.addUserTokenOrUpdateExisting(userToken);
                Cookie loginCookie = CookieFactory.getLoginCookie(userToken.getToken(), 30);
                response.addCookie(loginCookie);
                return true;
            }
            catch (Exception e){
                return false;
            }
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

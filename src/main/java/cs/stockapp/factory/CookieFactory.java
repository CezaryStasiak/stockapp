package cs.stockapp.factory;

import javax.servlet.http.Cookie;

public final class CookieFactory {

    public static final String LOGIN_COOKIE_NAME = "ac";

    public static Cookie getLoginCookie(String token, int lifeTimeInSeconds){
        Cookie cookie = new Cookie(LOGIN_COOKIE_NAME, token);
        cookie.setMaxAge(lifeTimeInSeconds);
        return cookie;
    }
}

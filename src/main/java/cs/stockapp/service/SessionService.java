package cs.stockapp.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public interface SessionService {

    int getUserIdIfIsAuthenticated(HttpServletRequest request);

    boolean loginUser(String userName, String password, HttpServletResponse response) throws NoSuchAlgorithmException;

    void logoutUser(HttpServletResponse response, int id);

}

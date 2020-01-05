package cs.stockapp.service;

import cs.stockapp.exception.UserNotFoundException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface SessionService {

    int getUserIdIfIsAuthenticated(HttpServletRequest request);

    boolean loginUser(String userName, String password, HttpServletResponse response) throws UserNotFoundException;

    void logoutUser(HttpServletResponse response, int id);

}

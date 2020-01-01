package cs.stockapp.interceptor;

import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;

    public SecurityInterceptor(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        int userId = sessionService.getUserIdIfIsAuthenticated(request);
        if (userId != -1){
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            return true;
        }
        response.sendRedirect(ActionsMappings.LOGIN_MAPPING);
        return false;
    }
}

package cs.stockapp.controller;

import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.mapping.ViewMappings;
import cs.stockapp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@Controller
public class AuthController {

    private final SessionService sessionService;

    @Autowired
    public AuthController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @GetMapping(ActionsMappings.LOGIN_MAPPING)
    public String login(HttpServletRequest request){
        if (sessionService.getUserIdIfIsAuthenticated(request) != -1){
            return "redirect:" + ActionsMappings.WELCOME_MAPPING;
        }
        return ViewMappings.LOGIN_VIEW;
    }

    @PostMapping(ActionsMappings.LOGIN_MAPPING)
    public String login(@RequestParam(defaultValue = "bla") String name,
                        @RequestParam(defaultValue = "bla") String password ,
                        HttpServletResponse response,
                        HttpServletRequest request) throws NoSuchAlgorithmException {

        if (sessionService.getUserIdIfIsAuthenticated(request) != -1){
            return "redirect:" + ActionsMappings.WELCOME_MAPPING;
        }

        if(sessionService.loginUser(name, password, response)){
            return "redirect:" + ActionsMappings.WELCOME_MAPPING;
        }

        return "redirect:" + ActionsMappings.LOGIN_MAPPING;

    }

    @GetMapping(ActionsMappings.LOGOUT_MAPPING)
    public String logout(HttpServletRequest request, HttpServletResponse response){

        int id = sessionService.getUserIdIfIsAuthenticated(request);
        if (id != -1){
            sessionService.logoutUser(response, id);
        }

        return "redirect:" + ActionsMappings.LOGIN_MAPPING;
    }
}

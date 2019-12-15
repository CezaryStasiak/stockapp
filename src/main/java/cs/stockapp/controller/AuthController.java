package cs.stockapp.controller;

import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.mapping.ErrorMessasges;
import cs.stockapp.mapping.ViewMappings;
import cs.stockapp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {

    private final SessionService sessionService;

    @Autowired
    public AuthController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @GetMapping(ActionsMappings.LOGIN_MAPPING)
    public String login(){
        return ViewMappings.LOGIN_VIEW;
    }

    @PostMapping(ActionsMappings.LOGIN_MAPPING)
    public String login(@RequestParam String name,
                        @RequestParam String password ,
                        HttpServletResponse response, Model model) {

        List<String> errors = new ArrayList<>();

        try{
            if(sessionService.loginUser(name, password, response)){
                return "redirect:" + ActionsMappings.WELCOME_MAPPING;
            }
            else{
                errors.add(ErrorMessasges.INVALID_LOGIN_OR_PASSWORD);
            }
        } catch (Exception e){
            errors.add(ErrorMessasges.DB_CONNECTION_ERROR);
        }

        model.addAttribute("errors", errors);
        return ActionsMappings.LOGIN_MAPPING;

    }

    @GetMapping(ActionsMappings.LOGOUT_MAPPING)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        int userId = (int) request.getSession().getAttribute("userId");
        sessionService.logoutUser(response, userId);
        return "redirect:" + ActionsMappings.LOGIN_MAPPING;
    }
}

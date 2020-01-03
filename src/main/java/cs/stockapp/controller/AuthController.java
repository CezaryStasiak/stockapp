package cs.stockapp.controller;

import cs.stockapp.mapping.ActionsMappings;
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

@Controller
public class AuthController {

    private final SessionService sessionService;

    @Autowired
    public AuthController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(ActionsMappings.LOGIN)
    public String login() {
        return ViewMappings.LOGIN_VIEW;
    }

    @PostMapping(ActionsMappings.LOGIN)
    public String login(@RequestParam String name,
                        @RequestParam String password,
                        HttpServletResponse response, Model model) {

        if (sessionService.loginUser(name, password, response)) {
            return "redirect:" + ActionsMappings.INVENTORY;
        }
        return ActionsMappings.LOGIN;

    }

    @GetMapping(ActionsMappings.LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        int userId = (int) request.getSession().getAttribute("userId");

        sessionService.logoutUser(response, userId);

        return "redirect:" + ActionsMappings.LOGIN;
    }
}

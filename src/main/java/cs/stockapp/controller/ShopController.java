package cs.stockapp.controller;

import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.mapping.ViewMappings;
import cs.stockapp.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopController {

    private final SessionServiceImpl sessionService;
    @Autowired
    public ShopController(SessionServiceImpl sessionService){
        this.sessionService = sessionService;
    }

    @GetMapping(ActionsMappings.WELCOME_MAPPING)
    public String welcome(HttpServletRequest httpServletRequest, HttpServletRequest request, Model model) {
        int userId = sessionService.getUserIdIfIsAuthenticated(request);
        if (userId == -1){
            return "redirect:" + ActionsMappings.LOGIN_MAPPING;
        }
        model.addAttribute("user", userId);
        return ViewMappings.WELCOME_VIEW;
    }
}

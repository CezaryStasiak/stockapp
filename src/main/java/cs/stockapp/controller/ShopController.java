package cs.stockapp.controller;

import cs.stockapp.dataaccess.JDBCUserManager;
import cs.stockapp.dataaccess.JDBCUserManagerImpl;
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

    private final JDBCUserManager jdbcUserManager;
    @Autowired
    public ShopController(JDBCUserManager jdbcUserManager){
         this.jdbcUserManager = jdbcUserManager;
    }

    @GetMapping(ActionsMappings.WELCOME_MAPPING)
    public String welcome(HttpServletRequest request, Model model) {
        Object userId = request.getSession().getAttribute("userId");
        model.addAttribute("user", userId);
        return ViewMappings.WELCOME_VIEW;
    }
}

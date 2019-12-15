package cs.stockapp.controller;

import cs.stockapp.dataaccess.JDBCUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class TestCrtl {

    private final JDBCUserManager jdbcUserManager;
    @Autowired
    public TestCrtl(JDBCUserManager jdbcUserManager){
        this.jdbcUserManager = jdbcUserManager;
    }

    @GetMapping("/greet")
    public String greeting(HttpServletRequest request, Model model) throws SQLException {
        Object userId = request.getSession().getAttribute("userId");
        model.addAttribute("name", userId);
        return "greeting";
    }
}

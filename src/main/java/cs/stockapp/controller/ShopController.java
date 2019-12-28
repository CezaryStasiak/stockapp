package cs.stockapp.controller;

import cs.stockapp.dataaccess.JDBCProductsManager;
import cs.stockapp.dataaccess.JDBCUserManager;
import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.mapping.ViewMappings;
import cs.stockapp.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {

    private final JDBCUserManager jdbcUserManager;
    private final JDBCProductsManager productsManager;

    @Autowired
    public ShopController(JDBCUserManager jdbcUserManager, JDBCProductsManager productsManager){
         this.jdbcUserManager = jdbcUserManager;
         this.productsManager = productsManager;
    }

    @GetMapping(ActionsMappings.INVENTORY_MAPPING)
    public String welcome(HttpServletRequest request, Model model) {

        Object userId = request.getSession().getAttribute("userId");

        List<Product> products = new ArrayList<>();

        try {
            products = productsManager.getProductsOnHandByUserId((int) userId);

            System.out.println(products);
        }
        catch (SQLException e){
        }

        model.addAttribute("products", products);
        return ViewMappings.INVENTORY_VIEW;
    }

}

package cs.stockapp.controller;

import cs.stockapp.dataaccess.JDBCProductsManager;
import cs.stockapp.dataaccess.JDBCUserManager;
import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.mapping.ErrorMessasges;
import cs.stockapp.mapping.ViewMappings;
import cs.stockapp.models.Product;
import cs.stockapp.models.ProductOnHand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(ActionsMappings.INVENTORY)
    public String welcome(HttpServletRequest request, Model model) {

        int userId = (int) request.getSession().getAttribute("userId");

        List<ProductOnHand> products = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try {
            products = productsManager.getProductsOnHandByUserId(userId);

        }
        catch (SQLException e){
            errors.add(ErrorMessasges.DB_CONNECTION_ERROR);
        }
        model.addAttribute("errors", errors);
        model.addAttribute("products", products);
        return ViewMappings.INVENTORY_VIEW;
    }

    @GetMapping(ActionsMappings.PRODUCTS)
    public String products(Model model){

        List<String> errors = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        try {
            products = productsManager.getCurrentlyAvailableProducts();
        }
        catch (SQLException e){
            errors.add(ErrorMessasges.DB_CONNECTION_ERROR);
        }
        model.addAttribute("products", products);
        model.addAttribute("errors", errors);
        return ViewMappings.PRODUCTS_VIEW;
    }

    @PostMapping(ActionsMappings.CHANGE_PRODUCT_QUANTITY)
    public String changeProductQuantity(Model model,
                                        HttpServletRequest request,
                                        @RequestParam int product,
                                        @RequestParam float quantity,
                                        @RequestParam String operation){

        List<String> errors = new ArrayList<>();

        if (operation == null){
            errors.add(ErrorMessasges.NOT_ALL_DATA_PROVIDED);
        }

        int userId = (int) request.getSession().getAttribute("userId");
        try {
            if (operation.equals("add")){
                productsManager.addOrUpdateProductOnHand(product, userId, quantity, false);
            } else if (operation.equals("sub")){
                productsManager.addOrUpdateProductOnHand(product, userId, -quantity, false);
            } else {
                productsManager.addOrUpdateProductOnHand(product, userId, quantity, true);
            }
        } catch (SQLException e){
            errors.add(ErrorMessasges.DB_CONNECTION_ERROR);
        }

        model.addAttribute("errors", errors);
        return "redirect:" + ActionsMappings.PRODUCTS;
    }

}

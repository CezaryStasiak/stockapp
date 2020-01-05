package cs.stockapp.controller;

import cs.stockapp.data.commands.ChangeQuantityForProductCommand;
import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.service.ProductsService;
import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.mapping.ViewMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShopController {

    private final ProductsService productsManager;

    @Autowired
    public ShopController(ProductsService productsManager) {
        this.productsManager = productsManager;
    }

    @GetMapping(ActionsMappings.INVENTORY)
    public String inventory(HttpServletRequest request, Model model) {

        int userId = (int) request.getSession().getAttribute("userId");

        List<ProductsOnHandQuantityModel> list = productsManager.getProductsOnHandByUserId(userId);

        model.addAttribute("products", list);

        return ViewMappings.INVENTORY_VIEW;
    }

    @GetMapping(ActionsMappings.PRODUCTS)
    public String products(Model model){

        List<ProductEntity> products = productsManager.getAllProducts();

        model.addAttribute("products", products);

        return ViewMappings.PRODUCTS_VIEW;
    }

    @PostMapping(ActionsMappings.ADD_PRODUCT_QUANTITY)
    public String addQuantity(HttpServletRequest request,
                              @ModelAttribute ChangeQuantityForProductCommand command) {

        int userId = (int) request.getSession().getAttribute("userId");
        command.setUserId(userId);
        productsManager.addQuantityForProduct(command);

        return "redirect:" + ActionsMappings.PRODUCTS;
    }

    @PostMapping(ActionsMappings.SUBSTRACT_PRODUCT_QUANTITY)
    public String subQuantity(HttpServletRequest request,
                              @RequestParam int product,
                              @RequestParam float quantity) {

        int userId = (int) request.getSession().getAttribute("userId");

        ChangeQuantityForProductCommand command = new ChangeQuantityForProductCommand(product, quantity);
        productsManager.substractQuantityForProduct(command);

        return "redirect:" + ActionsMappings.PRODUCTS;
    }

    @PostMapping(ActionsMappings.SET_PRODUCT_QUANTITY)
    public String setQuantity(HttpServletRequest request,
                              @RequestParam int product,
                              @RequestParam float quantity) {

        int userId = (int) request.getSession().getAttribute("userId");

        ChangeQuantityForProductCommand command = new ChangeQuantityForProductCommand(product, quantity);
        productsManager.setQuantityForProduct(command);

        return "redirect:" + ActionsMappings.PRODUCTS;
    }

}

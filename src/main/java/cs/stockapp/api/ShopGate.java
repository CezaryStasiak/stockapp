package cs.stockapp.api;

import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.data.entities.ProductsOnHandEntity;
import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.mapping.ViewMappings;
import cs.stockapp.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ShopGate {

    private final ProductsService productsService;

    @GetMapping("api/products")
    public List<ProductEntity> getAllProducts(@RequestParam("phrase") String phrase){
        return productsService.getByName(phrase);
    }

    @GetMapping("api/products/onhand")
    public List<ProductsOnHandQuantityModel> getAllProductsOnHand() {
        return productsService.getProductsOnHandForCurrentUser();
    }
}

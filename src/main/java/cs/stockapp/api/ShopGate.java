package cs.stockapp.api;

import cs.stockapp.data.entities.PagesOfProducts;
import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import cs.stockapp.service.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShopGate {

    private final ProductsService productsService;

    public ShopGate(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("api/products")
    public List<ProductEntity> getAllProducts(@RequestParam("phrase") String phrase){
        return productsService.getByName(phrase);
    }

    @GetMapping("api/products/onhand")
    public PagesOfProducts getAllProductsOnHand(@RequestParam("page") Integer p) {

        List<ProductsOnHandQuantityModel> products = productsService.getProductsOnHandForCurrentUser();

        int counter = 0;
        int page = 1;

        for (ProductsOnHandQuantityModel product : products) {
            if (counter >= 5) {
                counter = 0;
                page++;
            }

            product.setPageNumber(page);
            counter++;
        }

        return new PagesOfProducts(products.stream().filter(prod -> prod.getPageNumber() == p).collect(Collectors.toList()), page);

    }
}

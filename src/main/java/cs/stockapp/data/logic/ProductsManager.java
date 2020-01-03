package cs.stockapp.data.logic;

import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.data.entities.ProductsOnHandEntity;
import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import cs.stockapp.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsManager {

    private final ProductsOnHandRepositoryImpl productsOnHandRepository;
    private final ProductRepositoryImpl productRepository;
    private final UserRepositoryImpl userRepository;

    @Autowired
    public ProductsManager(ProductsOnHandRepositoryImpl productsOnHandRepository, ProductRepositoryImpl productRepository, UserRepositoryImpl userRepository) {
        this.productsOnHandRepository = productsOnHandRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<ProductEntity> getAllProducts(){
        return productRepository.getAll();
    }

    public List<ProductsOnHandQuantityModel> getProductsOnHandByUserId(int id){

        int shopId = userRepository.getUsersShopId(id);

        List<ProductsOnHandQuantityModel> resultList = new ArrayList<>();

        List<ProductsOnHandEntity> productsOnHandEntities = productsOnHandRepository.getAll();

        for (ProductsOnHandEntity p : productsOnHandEntities) {
            if (p.getShopId() == shopId){
                int productId = p.getProductId();
                resultList.add(new ProductsOnHandQuantityModel(productRepository.getOneById(productId), p.getAmount()));
            }
        }
        return resultList;
    }

    public void addQuantityForProduct(int productId, double productQuantity, int userId){

        int shopId = userRepository.getUsersShopId(userId);

        productsOnHandRepository.changeProductQuantity(shopId, productId, productQuantity);
    }

    public void substractQuantityForProduct(int productId, double productQuantity, int userId){

        int shopId = userRepository.getUsersShopId(userId);
        productQuantity = -productQuantity;
        productsOnHandRepository.changeProductQuantity(shopId, productId, productQuantity);
    }

    public void setQuantityForProduct(int productId, double quantity, int userId){

        int shopId = userRepository.getUsersShopId(userId);

        productsOnHandRepository.setProductQuantity(shopId, productId, quantity);
    }
}

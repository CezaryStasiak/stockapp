package cs.stockapp.service;

import cs.stockapp.data.commands.ChangeQuantityForProductCommand;
import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.data.entities.ProductsOnHandEntity;
import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import cs.stockapp.data.repositories.ProductRepositoryImpl;
import cs.stockapp.data.repositories.ProductsOnHandRepositoryImpl;
import cs.stockapp.data.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductsService {

    private final ProductsOnHandRepositoryImpl productsOnHandRepository;
    private final ProductRepositoryImpl productRepository;
    private final UserRepositoryImpl userRepository;
    private final UserProvider userProvider;

    @Autowired
    public ProductsService(ProductsOnHandRepositoryImpl productsOnHandRepository, ProductRepositoryImpl productRepository, UserRepositoryImpl userRepository, UserProvider userProvider) {
        this.productsOnHandRepository = productsOnHandRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userProvider = userProvider;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.getAll();
    }

    public List<ProductsOnHandQuantityModel> getProductsOnHandForCurrentUser() {

        int shopId = userRepository.getUsersShopId(userProvider.getCurrentUserId());

        List<ProductsOnHandQuantityModel> resultList = new ArrayList<>();

        List<ProductsOnHandEntity> productsOnHandEntities = productsOnHandRepository.getAll();

        for (ProductsOnHandEntity p : productsOnHandEntities) {
            if (p.getShopId() == shopId) {
                int productId = p.getProductId();
                resultList.add(new ProductsOnHandQuantityModel(productRepository.getOneById(productId), p.getAmount()));
            }
        }
        return resultList;
    }

    public void addQuantityForProduct(ChangeQuantityForProductCommand addQuantityCommand) {

        ProductsOnHandEntity product = getProductOrNewIfDoesntExists(addQuantityCommand);
        product.addQuantity(addQuantityCommand.getQuantity());
        productsOnHandRepository.save(product);

    }

    public void substractQuantityForProduct(ChangeQuantityForProductCommand substractQuantityCommand) {

        ProductsOnHandEntity product = getProductOrNewIfDoesntExists(substractQuantityCommand);
        product.substractQuantity(substractQuantityCommand.getQuantity());
        productsOnHandRepository.save(product);

    }

    public void setQuantityForProduct(ChangeQuantityForProductCommand setQuantityCommand) {

        ProductsOnHandEntity product = getProductOrNewIfDoesntExists(setQuantityCommand);
        product.setQuantity(setQuantityCommand.getQuantity());
        productsOnHandRepository.save(product);

    }

    private ProductsOnHandEntity getProductOrNewIfDoesntExists(ChangeQuantityForProductCommand command) {

        int shopId = userRepository.getUsersShopId(userProvider.getCurrentUserId());

        return productsOnHandRepository.getDistinctBy(shopId, command.getProductId())
                .orElse(new ProductsOnHandEntity(shopId, command.getProductId(), 0));
    }
}

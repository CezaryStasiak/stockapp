package cs.stockapp.service;

import cs.stockapp.data.commands.ChangeQuantityForProductCommand;
import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.data.entities.ProductsOnHandEntity;
import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import cs.stockapp.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private final ProductsOnHandRepositoryImpl productsOnHandRepository;
    private final ProductRepositoryImpl productRepository;
    private final UserRepositoryImpl userRepository;

    @Autowired
    public ProductsService(ProductsOnHandRepositoryImpl productsOnHandRepository, ProductRepositoryImpl productRepository, UserRepositoryImpl userRepository) {
        this.productsOnHandRepository = productsOnHandRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.getAll();
    }

    public List<ProductsOnHandQuantityModel> getProductsOnHandByUserId(int id) {

        int shopId = userRepository.getUsersShopId(id);

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
        int shopId = userRepository.getUsersShopId(command.getUserId());

        Optional<ProductsOnHandEntity> product =
                Optional.ofNullable(productsOnHandRepository.getDistinctBy(shopId, command.getProductId()));

        if (product.isPresent()) {
            return product.get();
        } else {
            return new ProductsOnHandEntity(shopId, command.getProductId(), 0);
        }
    }
}

package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductsOnHandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductsOnHandRepositoryImpl {

    @Autowired
    private ProductsOnHandRepository productOnHandRepository;

    public List<ProductsOnHandEntity> getAll(){
        return productOnHandRepository.findAll();
    }

    public void save(ProductsOnHandEntity productsOnHandEntity){
        productOnHandRepository.saveAndFlush(productsOnHandEntity);
    }

    public Optional<ProductsOnHandEntity> getDistinctBy(int shopId, int productId){
        return Optional.ofNullable(productOnHandRepository.getDistinctBy(shopId, productId));
    }

}

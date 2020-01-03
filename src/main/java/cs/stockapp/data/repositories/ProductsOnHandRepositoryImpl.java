package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductsOnHandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ProductsOnHandRepositoryImpl {

    @Autowired
    private ProductsOnHandRepository productOnHandRepository;

    public List<ProductsOnHandEntity> getAll(){
        return productOnHandRepository.findAll();
    }

    public void changeProductQuantity(int shopId, int productId, double quantity){
        productOnHandRepository.updateQuantity(shopId, productId, quantity);
    }

    public void setProductQuantity(int shopId, int productId, double quantity) {
        productOnHandRepository.setQuantity(shopId, productId, quantity);
    }
}

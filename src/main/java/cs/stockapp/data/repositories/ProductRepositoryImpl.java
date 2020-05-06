package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryImpl{

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAll(){
        return productRepository.findAll();
    }

    public List<ProductEntity> getByName(String phrase){
        return productRepository.findByName(phrase);
    }


    public ProductEntity getOneById(int id){ return productRepository.getOne(id);}
}

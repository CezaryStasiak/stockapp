package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductEntity;
import cs.stockapp.data.entities.ProductsOnHandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepositoryImpl{
    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAll(){
        return productRepository.findAll();
    }
    public ProductEntity getOneById(int id){ return productRepository.getOne(id);}
}

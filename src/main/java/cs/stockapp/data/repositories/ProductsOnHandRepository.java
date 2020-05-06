package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductsOnHandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsOnHandRepository extends JpaRepository<ProductsOnHandEntity, Integer> {

    @Query("SELECT product FROM ProductsOnHandEntity product WHERE product.shopId = ?1 AND product.productId = ?2")
    ProductsOnHandEntity getDistinctBy(int shopId, int productId);

    @Query("SELECT prod FROM ProductsOnHandEntity prod where prod.shopId= ?1")
    List<ProductsOnHandEntity> getAllBy(int shopId);
}

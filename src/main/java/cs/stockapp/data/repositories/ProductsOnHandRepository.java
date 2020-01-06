package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductsOnHandEntity;
import cs.stockapp.data.models.ProductsOnHandQuantityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsOnHandRepository extends JpaRepository<ProductsOnHandEntity, Integer> {

    @Query("SELECT product FROM ProductsOnHandEntity product WHERE product.shopId = ?1 AND product.productId = ?2")
    ProductsOnHandEntity getDistinctBy(int shopId, int productId);

    @Query(value = "SELECT product.id, product.name, product.price, product.unit, products_on_hand.amount\n" +
            "from product\n" +
            "join products_on_hand on product.id = products_on_hand.product_id AND products_on_hand.shop_id = ?1",
            nativeQuery = true)
    List<ProductsOnHandQuantityModel> getAllBy(int shopId);
}

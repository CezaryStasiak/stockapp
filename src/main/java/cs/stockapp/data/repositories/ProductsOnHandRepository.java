package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductsOnHandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductsOnHandRepository extends JpaRepository<ProductsOnHandEntity, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE ProductsOnHandEntity product SET product.amount = product.amount + ?3 " +
            "WHERE product.shopId = ?1 and product.productId = ?2")
    void updateQuantity(int shopId, int productId, double quantity);

    @Transactional
    @Modifying
    @Query("UPDATE ProductsOnHandEntity product SET product.amount = ?3" +
            " WHERE product.shopId = ?1 AND product.productId = ?2")
    void setQuantity(int shopId, int productId, double quantity);
}

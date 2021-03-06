package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT p FROM ProductEntity p WHERE lower(p.name) like lower(concat('%',?1,'%'))")
    List<ProductEntity> findByName(String name);
}

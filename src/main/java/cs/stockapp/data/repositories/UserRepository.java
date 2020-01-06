package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT user.id FROM UserEntity user WHERE user.username = ?1 AND user.password = ?2")
    int getUserId(String username, String password);

    @Query("SELECT user FROM UserEntity user WHERE user.username = ?1")
    UserEntity getDistinctByUsername(String username);
}

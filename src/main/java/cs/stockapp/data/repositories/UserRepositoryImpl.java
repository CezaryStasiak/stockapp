package cs.stockapp.data.repositories;

import cs.stockapp.data.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserByUsername(String username){
        return userRepository.getDistinctByUsername(username);
    }

    public int getUsersShopId(int userId){
        return userRepository.findById(userId).get().getShopId();
    }

    public int getUserId(String username) {
        return userRepository.getUserIdByUsername(username);
    }
}

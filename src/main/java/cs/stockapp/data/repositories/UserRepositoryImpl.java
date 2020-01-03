package cs.stockapp.data.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserRepositoryImpl {
    @Autowired
    private UserRepository userRepository;

    public int getShopIdByUserId(int userId){
        return userRepository.findById(userId).get().getShopId();
    }
}

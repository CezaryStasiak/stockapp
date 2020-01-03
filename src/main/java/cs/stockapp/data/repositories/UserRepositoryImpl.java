package cs.stockapp.data.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserRepositoryImpl {

    @Autowired
    private UserRepository userRepository;

    public int getUsersShopId(int userId){
        return userRepository.findById(userId).get().getShopId();
    }

    public int getUserId(String username, String password){
        return userRepository.getUserId(username, password);
    }
}

package cs.stockapp.service;

import cs.stockapp.data.repositories.UserRepositoryImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserProvider {

    private final UserRepositoryImpl userRepository;

    public UserProvider(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public int getCurrentUserId(){

        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        int userId = userRepository.getUserId(principal.getName());

        return  userId;
    }
}

package cs.stockapp.authentication;

import cs.stockapp.data.entities.UserEntity;
import cs.stockapp.authentication.UserEntityDetailsImpl;
import cs.stockapp.data.repositories.UserRepositoryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepositoryImpl userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.getUserByUsername(s);

        return new UserEntityDetailsImpl(userEntity);
    }
}

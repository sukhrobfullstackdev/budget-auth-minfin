package uz.fidobiznes.budgetauthminfin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import uz.fidobiznes.budgetauthminfin.entities.User;
import uz.fidobiznes.budgetauthminfin.repositories.UserRepository;

import java.util.Optional;

@Service
public class CustomLoadUser implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByLogin(username);
        if (optionalUser.isPresent()) {
            Mono<User> user = Mono.just(optionalUser.get());
            return user.block();
        } else {
            return null;
        }
    }
}

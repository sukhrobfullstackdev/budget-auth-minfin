package uz.fidobiznes.budgetauthminfin.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import uz.fidobiznes.budgetauthminfin.security.JWTProvider;
import uz.fidobiznes.budgetauthminfin.payload.ResponseDTO;
import uz.fidobiznes.budgetauthminfin.entities.User;
import uz.fidobiznes.budgetauthminfin.payload.LoginDTO;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    public AuthenticationService(AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public ResponseEntity<ResponseDTO> loginUser(LoginDTO loginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername());
            return ResponseEntity.ok(new ResponseDTO(true, "You are successfully logged in!", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, "Username or password is incorrect!"));
        }
    }


    @Transactional
    public ResponseEntity<Mono<ResponseDTO>> loginUserReactive(LoginDTO loginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));
            User user = (User) authenticate.getPrincipal();
            Mono<String> stringMono = jwtProvider.generateTokenReactive(user.getUsername());
            System.out.println(stringMono.block());
            return ResponseEntity.ok(Mono.just(new ResponseDTO(true, "You are successfully logged in!", stringMono.block())));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(new ResponseDTO(false, "Username or password is incorrect!")));
        }
    }
}

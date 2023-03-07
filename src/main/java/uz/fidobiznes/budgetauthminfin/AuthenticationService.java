package uz.fidobiznes.budgetauthminfin;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.fidobiznes.budgetauthminfin.dto.LoginDTO;

@Service
public class AuthenticationService  {
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    public AuthenticationService(AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public String loginUser(LoginDTO loginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));
            User user = (User) authenticate.getPrincipal();
            return jwtProvider.generateToken(user.getUsername());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}

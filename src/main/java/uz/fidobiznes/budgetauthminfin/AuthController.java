package uz.fidobiznes.budgetauthminfin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.fidobiznes.budgetauthminfin.dto.LoginDTO;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return authService.loginUser(loginDTO);
    }
//    @PostMapping("/login/reactive")
//    public ResponseEntity<ResponseDTO> loginReactive(@RequestBody LoginDTO loginDTO) {
//        return authService.loginUser(loginDTO);
//    }
}

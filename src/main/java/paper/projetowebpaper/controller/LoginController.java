package paper.projetowebpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paper.projetowebpaper.models.UserModel;
import paper.projetowebpaper.repository.UserRepository;
import paper.projetowebpaper.viewmodels.user.UserLoginViewModel;
import paper.projetowebpaper.viewmodels.user.UserViewModel;

import java.util.Optional;

@RestController
public class LoginController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(path = "/api/login")
    public ResponseEntity Login(@RequestBody UserLoginViewModel userLogin){
        Optional<UserModel> userOpt = repository.findByEmail(userLogin.email);

        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UserModel user = userOpt.get();

        boolean isValid = encoder.matches(userLogin.password, user.getPassword());

        if(isValid){
            UserViewModel userResult = new UserViewModel(user.id, user.name, user.email, user.createdAt, user.updatedAt);
            return ResponseEntity.status(HttpStatus.OK).body(userResult);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
    }
}

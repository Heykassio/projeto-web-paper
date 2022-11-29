package paper.projetowebpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import paper.projetowebpaper.models.UserModel;
import paper.projetowebpaper.repository.UserRepository;
import paper.projetowebpaper.viewmodels.user.UserRegisterViewModel;
import paper.projetowebpaper.viewmodels.user.UserUpdateViewModel;
import paper.projetowebpaper.viewmodels.user.UserViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(path = "/api/user")
    public ResponseEntity GetUsers(){
        List<UserModel> users = (List<UserModel>) repository.findAll();
        List<UserViewModel> usersReturn = new ArrayList<>();
        for (UserModel user: users) {
            usersReturn.add(new UserViewModel(user.id, user.name, user.email, user.createdAt, user.updatedAt));
        }
        ResponseEntity<List<UserViewModel>> res = ResponseEntity.ok().body(usersReturn);
        return res;

    }

    @GetMapping(path = "/api/user/{id}")
    public ResponseEntity GetUser(@PathVariable("id") Integer id){

        Optional<UserModel> userOpt = repository.findById(id);

        if(userOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        UserModel user = userOpt.get();

        UserViewModel userReturn = new UserViewModel(user.id, user.name, user.email, user.createdAt, user.updatedAt);

        return ResponseEntity.ok().body(userReturn);
    }

    @PostMapping(path = "/api/user")
    public ResponseEntity PostUser(@RequestBody UserRegisterViewModel user){
        Optional<UserModel> userOpt = repository.findByEmail(user.email);
        if(userOpt.isPresent()){
            return ResponseEntity.badRequest().body("E-mail já está em uso.");
        }

        UserModel userSave = new UserModel();
        userSave.setName(user.name);
        userSave.setEmail(user.email);
        userSave.setPassword(encoder.encode(user.password));
        userSave.setCreatedAt(LocalDateTime.now());
        userSave.setUpdatedAt(LocalDateTime.now());

        UserModel userReturn = repository.save(userSave);

        UserViewModel userResponse = new UserViewModel(userReturn.id, userReturn.name, userReturn.email, userReturn.createdAt, userReturn.updatedAt);

        return ResponseEntity.ok().body(userResponse);
    }

    @PutMapping(path = "/api/user/{id}")
    public ResponseEntity EditUser(@RequestBody UserUpdateViewModel user) {
        Optional<UserModel> userEditOpt = repository.findById(user.id);

        if(userEditOpt.isEmpty()){
            return ResponseEntity.badRequest().body("Usuário Inválido.");
        }

        UserModel userEdit = userEditOpt.get();
        userEdit.setName(user.name);
        userEdit.setPassword(user.password);
        userEdit.setEmail(user.email);
        userEdit.setUpdatedAt(LocalDateTime.now());

        repository.save(userEdit);

        return ResponseEntity.ok().body(new UserViewModel(userEdit.id, userEdit.name, userEdit.email, userEdit.createdAt, userEdit.updatedAt));
    }
}

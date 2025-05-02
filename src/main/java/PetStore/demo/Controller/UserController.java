package PetStore.demo.Controller;


import PetStore.demo.Models.User;
import PetStore.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void createUsers(){
        userService.createUser();

    }

    @GetMapping("/list")
        public List<User> listUsers(){

            return userService.listUsers();
        }



}

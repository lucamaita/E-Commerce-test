package com.SCAI.ecommerce.controller;

import com.SCAI.ecommerce.repository.UserRepository;
import com.SCAI.ecommerce.model.User;
import com.SCAI.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Classe Controller: API layer, gestisce le richieste HTTP e fornisce le conseguenti risposte
// una volta ricevuta la richiesta si interfaccia con il service per elaborare le richieste tramite la buisness logic
@RestController // RestController: i metodi del controller resituiranno direttamente i dati serializzati come risposte HTTP invece di HTML
@RequestMapping("/api/users")
public class UserController {

    @Autowired // Autowired permette l'iniezione automatica delle dipendenze (instanzia i bean necessari per l'iniezione)
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return user;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUser() throws Exception {
        List<User> users = userService.findAllUser();

        return users;
    }
    //
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserById(userId);

        userService.deleteUser(user, jwt);

        return "User deleted successfully";
    }

//    @PostMapping("/create")
//    public User createUser(@RequestBody User user) throws Exception {
//
//        User isExist = userRepository.findByEmail(user.getEmail());
//        if(isExist!=null) {
//            throw new Exception("user already exists with mail: " + user.getEmail());
//        }
//
//        User savedUser = userRepository.save(user);
//
//        return savedUser;
//    }
//    @DeleteMapping("/delete/{userId}")
//    public String deleteUser(@PathVariable Long userId) throws Exception {
//
//        userRepository.deleteById(userId);
//
//        return "User deleted successfully";
//    }

//    @GetMapping("/getAll")
//    public List<User> getAllUser() throws Exception {
//
//        List<User> users = userRepository.findAll();
//
//        return users;
//    }

//    public User findByEmail(String email) throws Exception{
//        User user = userRepository.findByEmail(email);
//        if(user==null){
//            throw new Exception("user not found with email"+email);
//        }
//        return user;
//    }

//    @GetMapping("/login/{mail}/{pw}")
//        public String login(@PathVariable String mail, @PathVariable String pw) throws Exception {
//        User user = userRepository.findByEmail(mail);
//        if (user != null && user.getPassword().equals(pw)) {
//            return "reindirizzamento alla prossima pagina";
//        } else {
//            return "L'indirizzo e-mail e la password non corrispondono. Riprova";
//        }
//    }
}

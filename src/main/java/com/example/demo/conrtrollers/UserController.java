package com.example.demo.conrtrollers;

import com.example.demo.entities.User;
import com.example.demo.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{user_Id}")
    public User getOneUser(@PathVariable Long user_Id) {
        return userRepository.findById(user_Id).orElse(null);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
             userRepository.save(foundUser);
            return foundUser;
        }else {
            return null;}
    }
    @DeleteMapping("/{user_id}")
    public void delete(Long user_id){
       userRepository.deleteById(user_id);
    }
}

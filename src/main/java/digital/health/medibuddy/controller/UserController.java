package digital.health.medibuddy.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import digital.health.medibuddy.model.User;
import digital.health.medibuddy.service.UserService;
import jakarta.mail.MessagingException;

@RestController
@CrossOrigin
public class UserController {
	private final UserService userService;
	
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<String> baseUrlMessage() {
        return ResponseEntity.ok("ALIMS Rest API");
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) throws IOException {
        return userService.addUser(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String identifier, @RequestParam String password) {
        String result = userService.login(identifier, password);

        if (!result.startsWith("Login denied")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam Long userId) {
        return userService.logout(userId);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.findByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{userId}/change-password")
    public ResponseEntity<String> changePassword(
            @PathVariable Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(userId, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) throws IOException {
        try {
            String result = userService.forgotPassword(email);
            return ResponseEntity.ok(result);
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    
}

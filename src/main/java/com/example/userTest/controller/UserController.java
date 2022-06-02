package com.example.userTest.controller;

import com.example.userTest.entity.UserEntity;
import com.example.userTest.exceptions.UserAlreadyExistException;
import com.example.userTest.exceptions.UserNotFoundException;
import com.example.userTest.repository.UserRepo;
import com.example.userTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return ResponseEntity.ok("User was saved successfully!");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Occurred error!");
        }
    }
    @GetMapping("/withoutPass")
    public ResponseEntity getOneUserWithoutPass(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOneWithoutPass(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Occurred error!");
        }
    }

    @GetMapping("/withPass")
    public ResponseEntity getOneUserWithPass(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOneWithPass(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Occurred error!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Occurred error!");
        }
    }
}

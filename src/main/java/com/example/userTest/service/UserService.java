package com.example.userTest.service;

import com.example.userTest.entity.UserEntity;
import com.example.userTest.exceptions.UserAlreadyExistException;
import com.example.userTest.exceptions.UserNotFoundException;
import com.example.userTest.repository.UserRepo;
import com.example.userTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistException("User with this name already exist!");
        }
        return userRepo.save(user);
    }

    public UserEntity getOneWithPass(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public User getOneWithoutPass(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return User.toModel(user);
    }

    public Long delete(Long id){
        userRepo.deleteById(id);
        return id;
    }
}

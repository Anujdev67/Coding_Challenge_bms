package com.springboot.BMS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.BMS.exception.InvalidUsernameException;
import com.springboot.BMS.model.User;
import com.springboot.BMS.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    public User signUp(User user) throws InvalidUsernameException {
        // Check for username duplicacy 
        Optional<User> optional = userRepository.findByUsername(user.getUsername());
        if (optional.isPresent()) {
            throw new InvalidUsernameException("Username already in use");
        }
        
        // Encrypt the password 
        String encryptedPass = passEncoder.encode(user.getPassword());
        user.setPassword(encryptedPass);
        
        return userRepository.save(user);
    }
}

package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public void registerNewUser(User user) {

        if(user.getName().isEmpty()) {
            throw new IllegalStateException("User name was empty");
        }
        userRepository.save(user);
    }
}

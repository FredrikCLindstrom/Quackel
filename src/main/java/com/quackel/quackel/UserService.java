package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public void registerNewUser(User user) {

        if(user.getName().isEmpty()) {
            throw new IllegalStateException("User name was empty");
        }
        userRepository.save(user);

    }

    public void changeUserById(Long id, String newName){
        Optional<User> UserToChange = userRepository.findById(id);
        if(UserToChange.isPresent()){
            User user1 = UserToChange.get();
            user1.setName(newName);
            userRepository.save(user1);
        }else{
            System.out.println("not found");
        }
    }
}

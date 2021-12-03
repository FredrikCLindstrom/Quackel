package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }


    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
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


    /*
    public ResponseEntity<User> changeUserById(Long id, String newName){
        try {
            Optional<User> UserToChange = userRepository.findById(id);

                User user1 = UserToChange.get();
                user1.setName(newName);
                userRepository.save(user1);

                return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException nse){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */
}

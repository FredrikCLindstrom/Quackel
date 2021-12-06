package com.quackel.quackel.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping(path="user/json/{id}")
    public ResponseEntity<User> getUserBydId(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="user/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> getUserBydIdXml(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Param("name") String name) {
        try {
            User newUser = new User();
            newUser.setName(name);
            userService.userRepository.save(newUser);
            String responseMessage = "New user " + newUser.getId() + " \"" + newUser.getName() + "\" created";
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> update(@Param("newName") @PathVariable("id") Long id, String newName) {
        try {
            User existUser = userService.findUserById(id);
            existUser.setName(newName);
            userService.userRepository.save(existUser);
            String responseMessage = "Updated user name for " + existUser.getId() + " to " + existUser.getName();
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            User existUser = userService.findUserById(id);
            userService.deleteUser(existUser);
            String responseMessage = existUser.getId() + " " + existUser.getName() + " has been deleted";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

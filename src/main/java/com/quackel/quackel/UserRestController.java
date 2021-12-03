package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
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

    // TODO - CREATE, DELETE

    @GetMapping(path="/all/json")
    public @ResponseBody
    Iterable<User> getAllUsersJson() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/all/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    Iterable<User> getAllUsersXml() {
        return userService.getAllUsers();
    }

    @GetMapping(path="user/{id}")
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

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> update(@Param("newName") @PathVariable("id") Long id, String newName) {
        try {
            User existUser = userService.findUserById(id);
            existUser.setName(newName);
            userService.userRepository.save(existUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

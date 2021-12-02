package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DummyFil {

    @Autowired
    UserService userService;

    @GetMapping(path="/all1")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/all2", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    Iterable<User> getAllUsers2() {
        return userService.getAllUsers();
    }
}

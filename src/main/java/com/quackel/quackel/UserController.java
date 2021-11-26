package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;

@Controller//tog bort restController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    QuackService quackService;

    @GetMapping("/test")
    public String testFunc(){
        return "hello";
    }
    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userService.getAllUsers();
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting.html";
    }


    @GetMapping("/start")
    public String testting2(Model model) {

        List<Quack> quackList = quackService.getAllQuacks();
        Collections.sort(quackList);//sorterar nyast längst up comparable i quack.java
        model.addAttribute("quackList", quackList);

        return "start.html";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {

        List <User> userList = userService.getAllUsers();
        User user = userList.get(id);

        model.addAttribute("user", user);

        return "userbyid.html";
    }
}


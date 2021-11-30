package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public String getUserById(@PathVariable("id") Long id, Model model) {

        List <User> userList = userService.getAllUsers();
        User user = userService.getUserById(id);

        model.addAttribute("user", user);

        return "userbyid.html";
    }

    @GetMapping("/quack/{id}")
    public String getPostbyId(@PathVariable("id") Long id, Model model) {
        List <Quack> quackList = quackService.getAllQuacks();
        Quack quack = quackService.getQuackById(id);
        model.addAttribute("quack", quack);

        return "quackbyid.html";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerNewUser() {
        return "register.html";
    }

    @RequestMapping(value ="/saveUser", method =RequestMethod.POST)
    public ModelAndView save(@ModelAttribute User user) {
        user.setName(user.getName());
        userService.registerNewUser(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new_user_info.html");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}


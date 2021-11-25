package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/index")
    public String testting(Model model) {
        List <User> userlist = userService.getAllUsers();

        User userOne = userlist.get(0);
        String nameOne= userOne.getName();

        User userTwo = userlist.get(1);
        String nameTwo= userTwo.getName();

        model.addAttribute("nameOne", nameOne);
        model.addAttribute("nameTwo", nameTwo);

        return "Index.html";
    }

    @GetMapping("/index2")
    public String testting2(Model model) {

        List<Quack> quackList = quackService.getAllQuacks();
        Quack quackOne = quackList.get(7);
        String bodyOne = quackOne.getBody();

        Quack quackTwo = quackList.get(1);
        String bodyTwo = quackTwo.getBody();

        model.addAttribute("bodyOne", bodyOne);
        model.addAttribute("bodyTwo", bodyTwo);

        List <User> userlist = userService.getAllUsers();

        User userOne = userlist.get(0);
        String nameOne= userOne.getName();

        User userTwo = userlist.get(1);
        String nameTwo= userTwo.getName();

        model.addAttribute("nameOne", nameOne);
        model.addAttribute("nameTwo", nameTwo);

        System.out.println(userOne.toString());
        System.out.println(quackOne.toString());

        return "Index.html";
    }
}


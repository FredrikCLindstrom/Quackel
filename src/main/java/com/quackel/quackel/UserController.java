package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

import java.util.*;
import java.util.stream.Collectors;


@Controller//tog bort restController
@RequestMapping("/api")
public class UserController {

    private String textField;

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

    @RequestMapping("/search/")
    public String requestUserById(@Param("searchTerm") String searchTerm, Model model) {

        List <User> userList = userService.getAllUsers();
        List <User> matchedSearchUserList = userList.stream().filter(user1 -> user1.getName().contains(searchTerm)).collect(Collectors.toList());
        int hits = matchedSearchUserList.size();

        System.out.println("matchedSearchUserList.size() = " + matchedSearchUserList.size());
        model.addAttribute("matchedSearchUserList", matchedSearchUserList);
        model.addAttribute("hits", hits);
        model.addAttribute("searchTerm", searchTerm);

        return "searchedUserReturn.html";
    }

    @DeleteMapping("/deleteQuack/{id}")
    public String deleteQuackById(@PathVariable("id") Long id) {
        Quack quack = quackService.getQuackById(id);
        Long userId = quack.getUser().getId();

        quackService.deleteQuackById(id);

        return "redirect:/api/user/" + userId;
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {

        userService.deleteUserById(id);

        return "redirect:/api/start";
    }

    @PutMapping("/changeUser/{id}")
    public String changeUserById(@Param("newName")@PathVariable("id") Long id, String newName) {
        System.out.println("is inside changeUserById ");
        userService.changeUserById(id,newName);

        return "redirect:/api/user/" + id;
    }

}


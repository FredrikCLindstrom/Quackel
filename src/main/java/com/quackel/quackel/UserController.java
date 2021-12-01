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

    @Autowired
    UserService userService;

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

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        List <User> userList = userService.getAllUsers();
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "userbyid.html";
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

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);

        return "redirect:/api/start";
    }

}


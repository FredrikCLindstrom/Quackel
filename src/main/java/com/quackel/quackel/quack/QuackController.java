package com.quackel.quackel.quack;

import com.quackel.quackel.user.User;
import com.quackel.quackel.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8090")
@RequestMapping("/api")
public class QuackController {

    @Autowired
    QuackService quackService;

    @Autowired
    UserService userService;

    @GetMapping("/start")
    public String getAllUsers(Model model) {
        List<Quack> quackList = quackService.getAllQuacks();
        Collections.sort(quackList);//sorterar nyast längst up comparable i quack.java
        model.addAttribute("quackList", quackList);

        return "start.html";
    }

    @GetMapping("/quack/{id}")
    public String getPostbyId(@PathVariable("id") Long id, Model model) {
        List <Quack> quackList = quackService.getAllQuacks();
        Quack quack = quackService.getQuackById(id);
        model.addAttribute("quack", quack);

        return "quackbyid.html";
    }

    @DeleteMapping("/deleteQuack/{id}")
    public String deleteQuackById(@PathVariable("id") Long id) {
        Quack quack = quackService.getQuackById(id);
        Long userId = quack.getUser().getId();
        quackService.deleteQuackById(id);

        return "redirect:/api/user/" + userId;
    }

    @GetMapping("/showUpdateQuack/{id}")
    public String showUpdateQuack(@PathVariable("id") Long id, Model model) {

        Quack quack = quackService.getQuackById(id);
        model.addAttribute("quack", quack);


        return "updatequackbyid.html";
    }

    @PutMapping("updateQuack/{id}")
    public String updateQuack(@PathVariable("id") Long id, String body) {

        Quack quack = quackService.getQuackById(id);
        quackService.updateQuackById(id, body);

        return "redirect:/api/user/" + quack.getUser().getId();

    }

    @PostMapping("/postQuack/{id}")
    public String postQuack(@PathVariable("id") Long id,String body){
        Quack quack = new Quack();
        quack.setBody(body);
        quack.setUserid(id);

        //User user = userService.getUserById(1002L);
        User user = new User();
        //user.setName("maaaaa");
        user.setId(id);

        //System.out.println(user.getId());
        quack.setUser(user);
        quackService.saveQuack(quack);
        System.out.println(quack.toString());
        return "redirect:/api/user/" + quack.getUser().getId();
    }
}

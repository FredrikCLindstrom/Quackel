package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api")
public class QuackController {

    @Autowired
    QuackService quackService;

    @GetMapping("/start")
    public String testting2(Model model) {
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
}

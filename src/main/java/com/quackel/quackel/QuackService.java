package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuackService {

    @Autowired
    QuackRepository quackRepository;
    @Autowired
    UserRepository userRepository;

    public List<Quack> getAllQuacks(){
        return quackRepository.findAll();
    }

    public Quack getQuackById(Long id) {
        return quackRepository.getById(id);
    }

    public void deleteQuackById(Long id) {
        quackRepository.deleteById(id);
    }

    public void updateQuackById(Long id, String body) {
        Quack quack = quackRepository.getById(id);
        quack.setBody(body);

        quackRepository.save(quack);
    }
    public void addQuack(Quack quack){
        quackRepository.save(quack);
    }

}

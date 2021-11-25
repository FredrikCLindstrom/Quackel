package com.quackel.quackel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuackService {

    @Autowired
    QuackRepository quackRepository;

    public List<Quack> getAllQuacks(){
        return quackRepository.findAll();
    }
}

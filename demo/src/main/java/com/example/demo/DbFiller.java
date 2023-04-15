package com.example.demo;

import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DbFiller implements CommandLineRunner {

    private ToDoRepository toDoRepository;

    @Autowired
    public DbFiller(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ToDo toDo1 = new ToDo("erstes ToDo", true);
        ToDo toDo2 = new ToDo("zweites ToDo", true);
        ToDo toDo3 = new ToDo("drittes ToDo", false);
        ToDo toDo4 = new ToDo("viertes ToDo", true);
        ToDo toDo5 = new ToDo("fuenftes ToDo", false);

        toDoRepository.saveAll(Arrays.asList(toDo1, toDo2, toDo3, toDo4, toDo5));

        System.out.println("TEST");
    }

}

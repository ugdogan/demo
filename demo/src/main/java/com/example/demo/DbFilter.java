package com.example.demo;

import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbFilter implements CommandLineRunner {

    private ToDoRepository toDoRepository;

    @Autowired
    public DbFilter(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ToDo toDo1 = new ToDo();
        ToDo toDo2 = new ToDo();
        ToDo toDo3 = new ToDo();
        ToDo toDo4 = new ToDo();
        ToDo toDo5 = new ToDo();

        toDo1.setDescription("erstes ToDo");
        toDo2.setDescription("zweites ToDo");
        toDo3.setDescription("drittes ToDo");
        toDo4.setDescription("viertes ToDo");
        toDo4.setDescription("fuenftes ToDo");

        toDo1.setDone(true);
        toDo2.setDone(true);
        toDo3.setDone(false);
        toDo4.setDone(true);
        toDo5.setDone(false);

        toDoRepository.save(toDo1);
        toDoRepository.save(toDo2);
        toDoRepository.save(toDo3);
        toDoRepository.save(toDo4);
        toDoRepository.save(toDo5);

        System.out.println("TEST");
    }

}

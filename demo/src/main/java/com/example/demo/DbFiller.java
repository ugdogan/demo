package com.example.demo;

import com.example.demo.enums.Role;
import com.example.demo.entity.ToDo;
import com.example.demo.entity.User;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
public class DbFiller implements CommandLineRunner {

    private ToDoRepository toDoRepository;

    @Autowired
    public DbFiller(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        ToDo toDo1 = new ToDo("erstes ToDo", true);
        ToDo toDo2 = new ToDo("zweites ToDo", true);
        ToDo toDo3 = new ToDo("drittes ToDo", false);
        ToDo toDo4 = new ToDo("viertes ToDo", true);
        ToDo toDo5 = new ToDo("fuenftes ToDo", false);

        toDoRepository.saveAll(Arrays.asList(toDo1, toDo2, toDo3, toDo4, toDo5));

        User user = new User("user", passwordEncoder.encode("user"), Set.of(Role.USER));
        User admin = new User("admin", passwordEncoder.encode("admin"), Set.of(Role.ADMIN, Role.USER, Role.ANALYST));
        User analyst = new User("analyst", passwordEncoder.encode("analyst"), Set.of(Role.ANALYST));


        userRepository.save(admin);
        userRepository.save(user);
        userRepository.save(analyst);

        System.out.println("TEST");
    }

}

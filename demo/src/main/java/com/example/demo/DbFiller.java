package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.ToDo;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
    public RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        roleRepository.saveAll(Arrays.asList(userRole, adminRole));

        User admin = new User("admin", passwordEncoder.encode("admin"));
        admin.addRole(userRole);
        admin.addRole(adminRole);
        User user = new User("user", passwordEncoder.encode("user"));
        user.addRole(userRole);

        userRepository.save(admin);
        userRepository.save(user);

        System.out.println("TEST");
    }

}

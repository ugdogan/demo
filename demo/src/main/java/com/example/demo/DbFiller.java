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
        ToDo toDo1 = new ToDo("erstes ToDo", true);
        ToDo toDo2 = new ToDo("zweites ToDo", true);
        ToDo toDo3 = new ToDo("drittes ToDo", false);
        ToDo toDo4 = new ToDo("viertes ToDo", true);
        ToDo toDo5 = new ToDo("fuenftes ToDo", false);

        toDoRepository.saveAll(Arrays.asList(toDo1, toDo2, toDo3, toDo4, toDo5));

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

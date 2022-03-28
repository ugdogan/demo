package com.example.demo.repository;

import com.example.demo.entity.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ToDoRepositoryTest {

    private ToDoRepository toDoRepository;

    @Autowired
    public ToDoRepositoryTest(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @BeforeEach
    public void dataFiller() {
        ToDo toDo1 = new ToDo();
        ToDo toDo2 = new ToDo();
        ToDo toDo3 = new ToDo();

        toDo1.setDescription("erstes ToDo");
        toDo2.setDescription("zweites ToDo");
        toDo3.setDescription("drittes ToDo");

        toDo1.setDone(true);
        toDo2.setDone(true);
        toDo3.setDone(false);

        toDoRepository.saveAll(Arrays.asList(toDo1, toDo2, toDo3));
    }

    @Test
    public void repositoryPopulatedSuccessfully() {
        List<ToDo> allToDos = (List<ToDo>) toDoRepository.findAll();
        Integer amountToDos = 3;

        assertEquals(allToDos.size(), amountToDos);
    }

    @Test
    public void findAllByIsDoneTest() {
        List<ToDo> allToDos = (List<ToDo>) toDoRepository.findAllByIsDone(true);
        Integer amountDoneToDos = 2;

        assertEquals(allToDos.size(), amountDoneToDos);
    }

    @Test
    public void findAllByIsNotDoneTest() {
        List<ToDo> allToDos = (List<ToDo>) toDoRepository.findAllByIsDone(false);
        Integer amountDoneToDos = 1;

        assertEquals(allToDos.size(), amountDoneToDos);
    }
}

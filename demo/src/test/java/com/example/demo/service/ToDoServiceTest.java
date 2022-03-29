package com.example.demo.service;

import com.example.demo.dto.UpdateDescriptionDto;
import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    private ToDoRepository repoMock;

    @InjectMocks
    private ToDoServiceImpl toDoServiceImpl;

    @Spy
    ModelMapper modelMapper;

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

        repoMock.saveAll(Arrays.asList(toDo1, toDo2, toDo3));
    }

    @Test
    public void readInvalidToDo() {
        assertThrows(EntityNotFoundException.class, () -> toDoServiceImpl.readToDo(5L));
    }

}

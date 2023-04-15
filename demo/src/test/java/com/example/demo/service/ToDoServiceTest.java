package com.example.demo.service;

import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    private ToDoRepository repoMock;

    @InjectMocks
    private ToDoServiceImpl toDoServiceImpl;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;

    @BeforeEach
    public void setup() {
        toDo1 = new ToDo();
        toDo2 = new ToDo();
        toDo3 = new ToDo();

        toDo1.setDescription("erstes ToDo");
        toDo2.setDescription("zweites ToDo");
        toDo3.setDescription("drittes ToDo");

        toDo1.setDone(true);
        toDo2.setDone(true);
        toDo3.setDone(false);
    }

    @Test
    public void deleteToDoTest() {
        toDoServiceImpl.deleteToDo(1L);

        verify(repoMock, times(1)).deleteById(1L);
    }

    @Test
    public void readToDo() {
        Long toDoId = 1L;
        ToDo expectedToDo = new ToDo();
        expectedToDo.setId(toDoId);
        expectedToDo.setDescription("Buy groceries");
        when(repoMock.findById(toDoId)).thenReturn(Optional.of(expectedToDo));

        ToDo actualToDo = toDoServiceImpl.readToDo(toDoId);

        assertEquals(expectedToDo, actualToDo);
    }

    @Test
    public void readInvalidToDo() {
        assertThrows(EntityNotFoundException.class, () -> toDoServiceImpl.readToDo(5L));
    }

    @Test
    public void readAllToDosTest() {
        // Arrange
        List<ToDo> expectedToDos = new ArrayList<>();
        expectedToDos.add(toDo1);
        expectedToDos.add(toDo2);
        expectedToDos.add(toDo3);

        when(repoMock.findAll()).thenReturn(expectedToDos);

        List<ToDo> actualToDos = toDoServiceImpl.readAllToDos();

        assertEquals(expectedToDos, actualToDos);
    }

    @Test
    public void readAllDoneToDos() {
        // Arrange
        when(repoMock.findAllByIsDone(true)).thenReturn(Arrays.asList(toDo1, toDo2));

        // Act
        List<ToDo> actualDoneToDos = toDoServiceImpl.readAllDoneToDos();

        // Assert
        assertEquals(2, actualDoneToDos.size());
        assertEquals(toDo1, actualDoneToDos.get(0));
        assertEquals(toDo2, actualDoneToDos.get(1));
    }

    @Test
    public void readAllNotDoneToDos() {
        // Arrange
        when(repoMock.findAllByIsDone(false)).thenReturn(Arrays.asList(toDo3));

        // Act
        List<ToDo> actualNotDoneToDos = toDoServiceImpl.readAllNotDoneToDos();

        // Assert
        assertEquals(1, actualNotDoneToDos.size());
        assertEquals(toDo3, actualNotDoneToDos.get(0));
    }

    @Test
    public void countAllDoneToDos() {
        when(repoMock.findAllByIsDone(true)).thenReturn(Arrays.asList(toDo1, toDo2));

        Integer actualCount = toDoServiceImpl.countAllDoneToDos();

        assertEquals(2, actualCount);
    }

    @Test
    public void countAllNotDoneToDos() {
        when(repoMock.findAllByIsDone(false)).thenReturn(Arrays.asList(toDo3));

        Integer actualCount = toDoServiceImpl.countAllNotDoneToDos();

        assertEquals(1, actualCount);
    }
}
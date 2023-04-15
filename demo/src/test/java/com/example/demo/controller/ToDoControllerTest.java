package com.example.demo.controller;

import com.example.demo.entity.ToDo;
import com.example.demo.service.ToDoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ToDoController.class)
class ToDoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToDoServiceImpl toDoServiceImpl;

    private List<ToDo> toDos;

    @BeforeEach
    public void dbfiller() {
        this.toDos = Arrays.asList(
                new ToDo(1L, "erstes ToDo", true),
                new ToDo(2L, "zweites ToDo", true),
                new ToDo(3L, "drittes ToDo", false)
        );
    }

    @Test
    void readToDoTest() throws Exception {
        when(toDoServiceImpl.readToDo(1L)).thenReturn(this.toDos.get(0));
        mvc.perform(MockMvcRequestBuilders
                        .get("/todo/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "description": "erstes ToDo",
                            "done": true
                        }
                        """
                ));
    }

    @Test
    void readAllToDosTest() throws Exception {
        when(toDoServiceImpl.readAllToDos()).thenReturn(this.toDos);
        mvc.perform(MockMvcRequestBuilders
                        .get("/todo")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "description": "erstes ToDo",
                                "done": true
                            },
                            {
                                "id": 2,
                                "description": "zweites ToDo",
                                "done": true
                            },
                            {
                                "id": 3,
                                "description": "drittes ToDo",
                                "done": false
                            }
                        ]
                        """));
    }

    @Test
    void createToDoTest() throws Exception {
        ToDo newToDo = new ToDo(4L, "viertes ToDo", true);
        when(toDoServiceImpl.createToDo(any())).thenReturn(newToDo);
        mvc.perform(MockMvcRequestBuilders
                        .post("/todo")
                        .content(asJsonString(new ToDo("viertes ToDo", true)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                          {
                                "id": 4,
                                "description": "viertes ToDo",
                                "done": true
                            }
                        """));
    }

    @Test
    void updateToDoTest() throws Exception {
        ToDo newToDo = new ToDo(1L, "NEU", true);
        when(toDoServiceImpl.updateToDo(any())).thenReturn(newToDo);
        mvc.perform(MockMvcRequestBuilders
                        .put("/todo")
                        .content(asJsonString(new ToDo(1L, "NEU", true)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                          {
                                "id": 1,
                                "description": "NEU",
                                "done": true
                            }
                        """));
    }

    @Test
    void deleteToDoTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/todo/{id}", 2))
                .andExpect(status().isAccepted());
    }

    @Test
    void readAllDoneToDosTest() throws Exception {
        List<ToDo> doneToDos = Arrays.asList(
                new ToDo(1L, "erstes ToDo", true),
                new ToDo(2L, "zweites ToDo", true)
        );
        when(toDoServiceImpl.readAllDoneToDos()).thenReturn(doneToDos);
        mvc.perform(MockMvcRequestBuilders
                        .get("/todo/done")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "description": "erstes ToDo",
                                "done": true
                            },
                            {
                                "id": 2,
                                "description": "zweites ToDo",
                                "done": true
                            }
                        ]
                        """));
    }

    @Test
    void readAllNotDoneToDosTest() throws Exception {
        List<ToDo> notDoneToDos = Arrays.asList(
                new ToDo(1L, "erstes ToDo", false),
                new ToDo(2L, "zweites ToDo", false)
        );
        when(toDoServiceImpl.readAllDoneToDos()).thenReturn(notDoneToDos);
        mvc.perform(MockMvcRequestBuilders
                        .get("/todo/notDone")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "description": "erstes ToDo",
                                "done": false
                            },
                            {
                                "id": 2,
                                "description": "zweites ToDo",
                                "done": false
                            }
                        ]
                        """));
    }

    @Test
    void countAllDoneToDosTest() throws Exception {
        when(toDoServiceImpl.countAllDoneToDos()).thenReturn(2);
        mvc.perform(MockMvcRequestBuilders
                        .get("/todo/countDone")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string("2"));
    }

    @Test
    void countAllNotDoneToDosTest() throws Exception {
        when(toDoServiceImpl.countAllNotDoneToDos()).thenReturn(2);
        mvc.perform(MockMvcRequestBuilders
                        .get("/todo/countNotDone")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string("2"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
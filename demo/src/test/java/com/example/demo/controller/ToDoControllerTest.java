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
        mvc.perform( MockMvcRequestBuilders
                .post("/todo")
                .content(asJsonString(new ToDo(4L, "viertes ToDo", true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        /*
                .andExpect(content().json("""
                          {
                                "id": 4,
                                "description": "viertes ToDo",
                                "done": true
                            }
                        """));

         */
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
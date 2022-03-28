package com.example.demo.service;

import com.example.demo.dto.CreateDescriptionDto;
import com.example.demo.dto.UpdateDescriptionDto;
import com.example.demo.entity.ToDo;

import java.util.List;

public interface ToDoService {

    public ToDo createToDo(CreateDescriptionDto toDo);
    public ToDo updateToDo(UpdateDescriptionDto toDo);
    public void deleteToDo(Long id);
    public ToDo readToDo(Long id);
    public List<ToDo> readAllToDos();
    public List<ToDo> readAllDoneToDos();
    public List<ToDo> readAllNotDoneToDos();
    public Integer countAllDoneToDos();
    public Integer countAllNotDoneToDos();
}

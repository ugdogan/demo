package com.example.demo.service;

import com.example.demo.dto.CreateDescriptionDto;
import com.example.demo.dto.UpdateDescriptionDto;
import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ToDo createToDo(CreateDescriptionDto toDo) {
        return toDoRepository.save(modelMapper.map(toDo, ToDo.class));
    }

    @Override
    public ToDo updateToDo(UpdateDescriptionDto toDo) {
        ToDo oldToDo = toDoRepository.findById(toDo.getId()).orElseThrow(
                () -> new EntityNotFoundException("TODO WURDE NICHT GEFUNDEN")
        );
        modelMapper.map(toDo, oldToDo);
        return toDoRepository.save(oldToDo);
    }

    @Override
    public void deleteToDo(Long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDo readToDo(Long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    @Override
    public List<ToDo> readAllToDos() {
        return (List<ToDo>) toDoRepository.findAll();
    }

    @Override
    public List<ToDo> readAllDoneToDos() {
        List<ToDo> allToDosList = (List<ToDo>) toDoRepository.findAll();
        List<ToDo> allDoneToDosList = new ArrayList<>();
        for (int i = 0; i < allToDosList.size(); i++) {
            ToDo toDo = allToDosList.get(i);
            if (toDo.getDone() == true) {
                allDoneToDosList.add(allToDosList.get(i));
            }
        }
        return allDoneToDosList;
    }

    @Override
    public List<ToDo> readAllNotDoneToDos() {
        List<ToDo> allToDosList = (List<ToDo>) toDoRepository.findAll();
        List<ToDo> allNotDoneToDosList = new ArrayList<>();
        for (int i = 0; i < allToDosList.size(); i++) {
            ToDo toDo = allToDosList.get(i);
            if (toDo.getDone() == false) {
                allNotDoneToDosList.add(allToDosList.get(i));
            }
        }
        return allNotDoneToDosList;
    }

    @Override
    public Integer countAllDoneToDos() {
        return this.readAllDoneToDos().size();
    }

    @Override
    public Integer countAllNotDoneToDos() {
        return this.readAllNotDoneToDos().size();
    }

}

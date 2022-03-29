package com.example.demo.service;

import com.example.demo.dto.CreateDescriptionDto;
import com.example.demo.dto.UpdateDescriptionDto;
import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ToDo createToDo(CreateDescriptionDto toDo) {
        return toDoRepository.save(modelMapper.map(toDo, ToDo.class));
    }

    @Override
    public ToDo updateToDo(UpdateDescriptionDto toDo) {
        ToDo oldToDo = toDoRepository.findById(toDo.getId()).orElseThrow(
                () -> new EntityNotFoundException(messageSource.getMessage("toDoNotFound", null, LocaleContextHolder.getLocale()))
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
        return toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @Override
    public List<ToDo> readAllToDos() {
        return (List<ToDo>)toDoRepository.findAll();
    }

    @Override
    public List<ToDo> readAllDoneToDos() {
        return (List<ToDo>) toDoRepository.findAllByIsDone(true);
    }

    @Override
    public List<ToDo> readAllNotDoneToDos() {
        return (List<ToDo>) toDoRepository.findAllByIsDone(false);
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

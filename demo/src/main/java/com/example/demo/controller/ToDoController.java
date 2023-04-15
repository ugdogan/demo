package com.example.demo.controller;

import com.example.demo.dto.CreateDescriptionDto;
import com.example.demo.dto.UpdateDescriptionDto;
import com.example.demo.entity.ToDo;
import com.example.demo.service.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    public ToDoServiceImpl toDoService;

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> readToDo(@PathVariable("id") Long id){
        return new ResponseEntity<>(toDoService.readToDo(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> readAllToDos(){
        return new ResponseEntity<>(toDoService.readAllToDos(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable("id") Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody CreateDescriptionDto toDo) {
        return new ResponseEntity<>(toDoService.createToDo(toDo), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ToDo> updateToDo(@RequestBody UpdateDescriptionDto toDo) {
        return new ResponseEntity<>(toDoService.updateToDo(toDo), HttpStatus.OK);
    }

    @GetMapping("/done")
    public ResponseEntity<List<ToDo>> readAllDoneToDos() {
        return new ResponseEntity<>(toDoService.readAllDoneToDos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/notDone")
    public ResponseEntity<List<ToDo>> readAllNotDoneToDos() {
        return new ResponseEntity<>(toDoService.readAllNotDoneToDos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/countDone")
    public ResponseEntity<Integer> countAllDoneToDos() {
        return new ResponseEntity<>(toDoService.countAllDoneToDos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/countNotDone")
    public ResponseEntity<Integer> countAllNotDoneToDos() {
        return new ResponseEntity<>(toDoService.countAllNotDoneToDos(), HttpStatus.ACCEPTED);
    }
}

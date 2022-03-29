package com.example.demo.controller;

import com.example.demo.aspects.MonitorTime;
import com.example.demo.dto.CreateDescriptionDto;
import com.example.demo.dto.UpdateDescriptionDto;
import com.example.demo.entity.ToDo;
import com.example.demo.service.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    public ToDoServiceImpl toDoService;

    @MonitorTime
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody CreateDescriptionDto toDo) {
        return new ResponseEntity<>(toDoService.createToDo(toDo), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ToDo> updateToDo(@RequestBody UpdateDescriptionDto toDo) {
        return new ResponseEntity<>(toDoService.updateToDo(toDo), HttpStatus.ACCEPTED);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

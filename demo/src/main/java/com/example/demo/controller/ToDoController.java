package com.example.demo.controller;

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

    @GetMapping("/{id}")
    public ToDo readToDo(@PathVariable("id") Long id){
        return toDoService.readToDo(id);
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> readAllToDos(){
        return new ResponseEntity<>(toDoService.readAllToDos(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable("id") Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody CreateDescriptionDto toDo) {
        return new ResponseEntity<>(toDoService.createToDo(toDo), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<ToDo> updateToDo(@RequestBody UpdateDescriptionDto toDo) {
        return new ResponseEntity<>(toDoService.updateToDo(toDo), HttpStatus.ACCEPTED);
    }

    @GetMapping("/done")
    public List<ToDo> readAllDoneToDos() {
        return toDoService.readAllDoneToDos();
    }

    @GetMapping("/notDone")
    public List<ToDo> readAllNotDoneToDos() {
        return toDoService.readAllNotDoneToDos();
    }

    @GetMapping("/countDone")
    public Integer countAllDoneToDos() {
        return toDoService.countAllDoneToDos();
    }

    @GetMapping("/countNotDone")
    public Integer countAllNotDoneToDos() {
        return toDoService.countAllNotDoneToDos();
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
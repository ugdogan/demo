package com.example.demo.repository;

import com.example.demo.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    List<ToDo> findAllByIsDone(Boolean isDone);
}

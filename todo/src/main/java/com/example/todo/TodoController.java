package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository; // TodoRepository를 사용하기 위해 주입


    // TODO 항목 추가
    @PostMapping("/add")
    public Todo addTodo(@RequestParam String task) {
        Todo todo = new Todo();
        todo.setTask(task); // todo 저장
        todo.setCompleted(false); // 새로 추가된 TODO 진행상황은 미진행(false)상태로 지정
        todoRepository.save(todo); // 데이터베이스에 저장
        return todo; // 추가된 TODO 항목 반환
    }

    // 모든 TODO 항목 조회
    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoRepository.findAll(); // JSON 형식으로 반환
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
    
    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestParam(required = false) Boolean completed) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        if (completed != null) {
            todo.setCompleted(completed);
        }
        todoRepository.save(todo);
        return todo;
    }
}
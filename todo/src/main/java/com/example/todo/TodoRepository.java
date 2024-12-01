package com.example.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // JpaRepository를 상속받아 기본적인 CRUD 기능을 제공
}
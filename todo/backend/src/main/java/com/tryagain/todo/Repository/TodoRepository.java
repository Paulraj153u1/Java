package com.tryagain.todo.Repository;

import com.tryagain.todo.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}

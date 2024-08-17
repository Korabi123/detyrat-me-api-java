package com.example.demo.TodoAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoAPI {
  private final List<TodoModel> todoList = new ArrayList<>();

  @GetMapping("api/todos/get")
  public List<TodoModel> getTodos(
    @RequestParam(name = "title", required = false) String title,
    @RequestParam(name = "description", required = false) String description,
    @RequestParam(name = "isComplete", required = false) boolean isComplete,
    @RequestParam(name = "id", required = false) String id
  ) {
    return todoList.stream()
      .filter(todo -> (title == null || todo.getTitle().equals(title))
        && (description == null || todo.getDescription().equals(description))
        && (isComplete == todo.isComplete())
        && (id == null || todo.getId().equals(id)))
      .collect(Collectors.toList());
  }

  @PostMapping("api/todos/create")
  public List<TodoModel> createTodo(
    @RequestBody List<TodoModel> newTodos
  ) {
    for (TodoModel todo : newTodos) {
      this.todoList.add(todo);
    }
    return this.todoList;
  }

  @PatchMapping("api/todos/update")
  public List<TodoModel> updateTodo(
    @RequestParam("id") String id,
    @RequestBody TodoModel updatedTodo
  ) {
    todoList.stream()
      .filter(todo -> todo.getId().equals(id))
      .findFirst()
      .ifPresent(todo -> {
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setComplete(updatedTodo.isComplete());
      });
    return todoList;
  }

  @DeleteMapping("api/todos/delete")
  public List<TodoModel> deleteTodo(
    @RequestParam("id") String id
  ) {
    todoList.removeIf(todo -> todo.getId().equals(id));
    return todoList;
  }
}

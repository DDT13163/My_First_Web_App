package com.example.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<ToDo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new ToDo(++todosCount, "in28minutes", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new ToDo(++todosCount, "in28minutes", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new ToDo(++todosCount, "in28minutes", "Learn Full Stack", LocalDate.now().plusYears(3), false));
    }

    public List<ToDo> findByUserName(String userName) {
        return todos;
    }

    public void addTodo(String userName, String description, LocalDate targetDate, boolean done) {
        ToDo toDo = new ToDo(++todosCount, userName, description, targetDate, done);
        todos.add(toDo);
    }

    public void deleteById(int id) {
        Predicate<? super ToDo> predicate = toDo -> toDo.getId() == id;
        todos.removeIf(predicate);
    }

    public ToDo findById(int id) {
        Predicate<? super ToDo> predicate = toDo -> toDo.getId() == id;
        ToDo toDo = todos.stream().filter(predicate).findFirst().get();
        return toDo;
    }

    public void updateTodo(ToDo toDo) {
        deleteById(toDo.getId());
        todos.add(toDo);
    }
}

package com.fortinet.fpc.todo.controller;

import com.fortinet.fpc.todo.entity.Status;
import com.fortinet.fpc.todo.entity.TodoItem;
import com.fortinet.fpc.todo.service.TodoItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoItemRestApi {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/todoitems")
    public List<TodoItem> getTodoItems(){

        return todoItemService.getTodoItems();
    }

    @GetMapping("/todoitems/{todoItemId}")
    public TodoItem getTodoItem(@PathVariable int todoItemId) {

        TodoItem todoItem = todoItemService.getTodoItem(todoItemId);
        if (todoItem == null) {
            throw new TodoItemNotFpundException("Customer id not found - " + todoItemId);
        }

        return todoItem;
    }

    @PostMapping("/todoitems")
    public TodoItem addTodoItem(@RequestBody TodoItem todoItem){

        todoItem.setId(0);
        Date date = new Date();
        todoItem.setCreateTime(date.toString());
        todoItem.setModifiedTime(date.toString());
        todoItemService.saveTodoItem(todoItem);

        return todoItem;
    }

    @PutMapping("/todoitems")
    public TodoItem updateTodoItem(@RequestBody TodoItem todoItem) {

        TodoItem tempTodoItem = todoItemService.getTodoItem(todoItem.getId());
        Date date = new Date();
        todoItem.setModifiedTime(date.toString());
        todoItem.setCreateTime(tempTodoItem.getCreateTime());
        for (Status status : tempTodoItem.getStatuses()){
            todoItem.add(status);
        }
        todoItemService.saveTodoItem(todoItem);
        return todoItem;
    }

    @DeleteMapping("/todoitems/{todoItemId}")
    public String deleteTodoItem (@PathVariable int todoItemId) {

        TodoItem tempTodoItem  = todoItemService.getTodoItem(todoItemId);

        if (tempTodoItem == null) {
            throw new TodoItemNotFpundException("TodoItem id not found - " + todoItemId);
        }

        todoItemService.deleteTodoItem(todoItemId);

        return "Deleted toDoItem id - " + todoItemId;
    }
}

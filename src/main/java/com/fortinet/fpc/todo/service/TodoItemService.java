package com.fortinet.fpc.todo.service;

import com.fortinet.fpc.todo.dao.TodoItemDao;
import com.fortinet.fpc.todo.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemDao todoItemDAO;

    @Transactional
    public List<TodoItem> getTodoItems() {
        return todoItemDAO.getTodoItems();
    }

    @Transactional
    public void saveTodoItem(TodoItem theTodoItem) {
        todoItemDAO.saveTodoItem(theTodoItem);
    }

    @Transactional
    public TodoItem getTodoItem(int theId) {

        return todoItemDAO.getTodoItem(theId);
    }

    @Transactional
    public void deleteTodoItem(int theId) {

        todoItemDAO.deleteTodoItem(theId);
    }

}

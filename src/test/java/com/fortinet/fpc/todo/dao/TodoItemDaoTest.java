package com.fortinet.fpc.todo.dao;


import com.fortinet.fpc.todo.entity.Status;
import com.fortinet.fpc.todo.entity.TodoItem;
import com.fortinet.fpc.todo.todoConfig.TodoAppConfig;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TodoAppConfig.class})
public class TodoItemDaoTest {

    @Autowired
    private TodoItemDao todoItemDao;

    private List<TodoItem> todoItems;
    private TodoItem todoItem;
    private TodoItem todoItem2;
    private Status status;

    @Before
    public void setup() {
        status = new Status("create");
        status.setId(1);
        todoItems = new ArrayList<>();
        todoItem = new TodoItem();
        todoItem2 = new TodoItem();
        todoItem.setCreateTime("0000");
        todoItem.setModifiedTime("0000");
        todoItem.setContent("test");
        todoItem.setTitle("test1");
//        todoItem.setId(1);
        todoItem2.setId(2);
        todoItem2.setCreateTime("0000");
        todoItem2.setModifiedTime("0000");
        todoItem2.setTitle("test2");
        todoItem2.setContent("test");
        todoItem2.add(status);
        todoItem.add(status);
        todoItems.add(todoItem);
    }


    @Rollback(true)
    @Transactional
    @Test
    public void getTodoItems() {
    }


    @Rollback(true)
    @Transactional
    @Test
    public void saveTodoItem() {
        todoItemDao.saveTodoItem(todoItem2);

        TodoItem tempTodoItem = todoItemDao.getTodoItem(2);
        Assert.assertEquals(todoItem2.getTitle(), tempTodoItem.getTitle());
    }


    @Rollback(true)
    @Transactional
    @Test
    public void deleteTodoItem() {

        todoItemDao.saveTodoItem(todoItem);
        List<TodoItem> tempTodoItems = todoItemDao.getTodoItems();
        Assert.assertEquals(todoItem.getTitle(), tempTodoItems.get(tempTodoItems.size()-1).getTitle());
    }
}
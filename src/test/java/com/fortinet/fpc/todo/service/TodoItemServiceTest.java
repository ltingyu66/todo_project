package com.fortinet.fpc.todo.service;

import com.fortinet.fpc.todo.dao.TodoItemDao;
import com.fortinet.fpc.todo.entity.Status;
import com.fortinet.fpc.todo.entity.TodoItem;
import com.fortinet.fpc.todo.todoConfig.TodoAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TodoAppConfig.class})
public class TodoItemServiceTest {

    @Mock
    private TodoItemDao todoItemDao;

    @InjectMocks
    private TodoItemService todoItemService;

    private List<TodoItem> todoItems;
    private TodoItem todoItem;
    private TodoItem todoItem2;
    private Status status;

    @Before
    public void setup() {
        todoItemService = new TodoItemService();
        MockitoAnnotations.initMocks(this);
        status = new Status("create");
        status.setId(1);
        todoItems = new ArrayList<>();
        todoItem = new TodoItem();
        todoItem2 = new TodoItem();
        todoItem.setCreateTime("0000");
        todoItem.setModifiedTime("0000");
        todoItem.setContent("test");
        todoItem.setTitle("test1");
        todoItem.setId(1);
        todoItem2.setId(2);
        todoItem2.setCreateTime("0000");
        todoItem2.setModifiedTime("0000");
        todoItem2.setTitle("test2");
        todoItem2.setContent("test");
        todoItem2.add(status);
        todoItem.add(status);
        todoItems.add(todoItem);
    }

    @Test
    public void getTodoItems() {
        Mockito.when(todoItemDao.getTodoItems()).thenReturn(todoItems);
        assertEquals(todoItems,todoItemService.getTodoItems());
    }

    @Test
    public void saveTodoItem() {
        todoItemService.saveTodoItem(todoItem);
        verify(todoItemDao).saveTodoItem(any(TodoItem.class));
    }

    @Test
    public void getTodoItem() {
        Mockito.when(todoItemDao.getTodoItem(todoItem.getId())).thenReturn(todoItem);
        assertEquals(todoItem,todoItemService.getTodoItem(todoItem.getId()));
    }

    @Test
    public void deleteTodoItem() {
        todoItemService.deleteTodoItem(todoItem.getId());
        verify(todoItemDao).deleteTodoItem(todoItem.getId());
    }
}

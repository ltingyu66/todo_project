package com.fortinet.fpc.todo.controller;

import com.fortinet.fpc.todo.entity.Status;
import com.fortinet.fpc.todo.entity.TodoItem;
import com.fortinet.fpc.todo.service.TodoItemService;
import com.fortinet.fpc.todo.todoConfig.TodoAppConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TodoAppConfig.class})
public class TodoItemRestApiTest {

    private static Logger logger = LoggerFactory.getLogger(TodoItemRestApiTest.class);
    private MockMvc mockMvc;
    @Mock
    private  TodoItemService todoItemService;

    @InjectMocks
    private TodoItemRestApi todoItemRestApi = new TodoItemRestApi();


    List<TodoItem> todoItems;
    TodoItem todoItem;
    TodoItem todoItem2;
    Status status;
    int wrongId = 50;

    TodoItemNotFpundException notFpundException;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoItemRestApi).build();

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
        notFpundException = new TodoItemNotFpundException("TodoItem id not found - " + wrongId);
    }

    @Test
    public void getTodoItems() throws Exception {
        Mockito.when(todoItemService.getTodoItems()).thenReturn(todoItems);

        MvcResult result = mockMvc.perform(get("/api/todoitems"))
                .andExpect(status().isOk())
                .andReturn();
        String expected = "[{\"id\":1,\"createTime\":\"0000\",\"modifiedTime\":\"0000\",\"title\":\"test1\",\"content\":\"test\",\"statuses\":[{\"id\":1,\"title\":\"create\"}]}]";
        logger.info(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

    }

    @Test
    public void getTodoItem() throws Exception{
        Mockito.when(todoItemService.getTodoItem(todoItem.getId())).thenReturn(todoItem);

        MvcResult result = mockMvc.perform(get("/api/todoitems/{todoItemId}",todoItem.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String expected = "{\"id\":1,\"createTime\":\"0000\",\"modifiedTime\":\"0000\",\"title\":\"test1\",\"content\":\"test\",\"statuses\":[{\"id\":1,\"title\":\"create\"}]}";
        logger.info(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void addTodoItem() throws Exception {
        String JSON = "{\"id\":2,\"createTime\":\"0000\",\"modifiedTime\":\"0000\",\"title\":\"test2\",\"content\":\"test\",\"statuses\":[{\"id\":1,\"title\":\"create\"}]}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/todoitems")
                .accept(MediaType.APPLICATION_JSON).content(JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        String expected = "{\"id\":0,\"createTime\":\"0000\",\"modifiedTime\":\"0000\",\"title\":\"test2\",\"content\":\"test\",\"statuses\":[{\"id\":1,\"title\":\"create\"}]}";
        logger.info(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void updateTodoItem() throws Exception {
        String JSON = "{\"id\":2,\"createTime\":\"0000\",\"modifiedTime\":\"0000\",\"title\":\"test2\",\"content\":\"test\",\"statuses\":[{\"id\":1,\"title\":\"create\"}]}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/todoitems")
                .accept(MediaType.APPLICATION_JSON).content(JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        String expected = "{\"id\":2,\"createTime\":\"0000\",\"modifiedTime\":\"0000\",\"title\":\"test2\",\"content\":\"test\",\"statuses\":[{\"id\":1,\"title\":\"create\"}]}";
        logger.info(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void deleteTodoItem() throws Exception {
        Mockito.when(todoItemService.getTodoItem(todoItem.getId())).thenReturn(todoItem);
        MvcResult result = mockMvc.perform(delete("/api/todoitems/{todoItemId}",todoItem.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String expected = "Deleted toDoItem id - 1";
        logger.info(result.getResponse().getContentAsString());
        assertEquals(expected,result.getResponse().getContentAsString());
    }
}

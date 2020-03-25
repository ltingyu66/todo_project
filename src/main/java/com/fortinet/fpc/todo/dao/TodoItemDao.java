package com.fortinet.fpc.todo.dao;

import com.fortinet.fpc.todo.entity.TodoItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoItemDao {
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    public List<TodoItem> getTodoItems() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<TodoItem> theQuery =
                currentSession.createQuery("from TodoItem order by title",
                        TodoItem.class);

        // execute query and get result list
        List<TodoItem> todoItems = theQuery.getResultList();

        // return the results
        return todoItems;
    }

    public void saveTodoItem(TodoItem theTodoItem) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save/upate the customer ... finally LOL
        currentSession.saveOrUpdate(theTodoItem);

    }

    public TodoItem getTodoItem(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        TodoItem theTodoItem= currentSession.get(TodoItem.class, theId);

        return theTodoItem;
    }

    public void deleteTodoItem(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        // delete object with primary key
        TodoItem tempTodoItem = currentSession.get(TodoItem.class, theId);
        
        currentSession.delete(tempTodoItem);

       
    }

}

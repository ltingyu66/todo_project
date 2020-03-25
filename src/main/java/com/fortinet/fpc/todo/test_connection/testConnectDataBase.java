package com.fortinet.fpc.todo.test_connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class testConnectDataBase {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/todo-project?useSSL=false&serverTimezone=UTC";
        String user = "springstudent";
        String pass = "springstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful!!!");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}

package main.java.edu.epam.selctioncomittee;

import main.java.edu.epam.selctioncomittee.connection.factories.ConnectionFactory;
import main.java.edu.epam.selctioncomittee.connection.factories.MySqlConnectionFactory;
import main.java.edu.epam.selctioncomittee.connection.factories.SqliteConnectionFactory;
import main.java.edu.epam.selctioncomittee.service.LogicService;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("resources/application.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String databaseType = props.getProperty("DB_TYPE");
        ConnectionFactory connectionFactory = getConnectionFactory(databaseType);
        LogicService logicService = new LogicService(connectionFactory);
        logicService.start();

        //Создает объект себе для отправки запросов SQL к базе данных
        Connection connection = connectionFactory.getConnection().getConnection();
        Statement st = null;
        try {
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Получаем результирующую таблицу
        ResultSet rs = null;
        try {
            rs = st.executeQuery("SELECT * FROM faculty WHERE id = 1;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Получаем данные
        int x = 0;
        try {
            x = rs.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                for (int i = 1; i <= x; i++) {
                    System.out.println(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (st != null) try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ConnectionFactory getConnectionFactory(String databaseType) {
        switch (databaseType) {
            case "mysql":
                return new MySqlConnectionFactory();
            default:
                return new SqliteConnectionFactory();
        }
    }
}


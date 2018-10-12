package edu.epam.selctioncomittee;

import java.sql.*;

public class Main {

    private static Connection connection = null;
    private static String username = "root";
    private static String password = "root";
//    private static String url = "jdbc:sqlite:D:\\Git\\EPAM-Java\\selection.committee\\src\\main\\resources\\university.s3db";
    private static String url = "jdbc:mysql://localhost:3306/university?useSSL=false&serverTimezone=UTC";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //соединяемся
        if (connection != null) {
            System.out.println("Соединение успешно выполнено");
        } else {
            System.out.println("Соединение не установлено");
            System.exit(0);
        }

        //Создает объект себе для отправки запросов SQL к базе данных
        Statement st = null;
        try {
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Получаем результирующую таблицу
        ResultSet rs = null;
        try {
            rs = st.executeQuery("SELECT * FROM faculty WHERE faculty_id = 1;");
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

}


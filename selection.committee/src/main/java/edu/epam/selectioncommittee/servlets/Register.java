/*package edu.epam.selectioncommittee.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

*//**
 * Created by mascon on 07.11.2018.
 *//*

public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString();
        resp.getWriter().write(jsonString);
        if ("POST".equalsIgnoreCase(req.getMethod())) {
            test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello");
    }
}*/

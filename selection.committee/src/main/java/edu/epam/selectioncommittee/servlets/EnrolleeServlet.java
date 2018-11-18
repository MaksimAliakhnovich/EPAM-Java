package edu.epam.selectioncommittee.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.dao.factories.MySqlDAOFactory;
import edu.epam.selectioncommittee.dao.factories.SqliteDAOFactory;
import edu.epam.selectioncommittee.entity.Enrollee;
import edu.epam.selectioncommittee.service.EnrolleeService;
import edu.epam.selectioncommittee.utils.ConfigurationManager;
import edu.epam.selectioncommittee.utils.DBConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by mascon on 07.11.2018.
 */

public class EnrolleeServlet extends HttpServlet {
    private EnrolleeService enrolleeService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        if (req.getHeader("type").equals("add")) {
            Enrollee enrollee = mapper.readValue(requestBody, Enrollee.class);
            enrolleeService.addEnrollee(enrollee.getFirstName(), enrollee.getLastName(), enrollee.getCertificateScore(), enrollee.getPassport());
            resp.getWriter().write(mapper.writeValueAsString(enrolleeService.getAllEnrollee()));
        } else {
            String passport = mapper.readTree(requestBody).get("passport").asText();
            resp.getWriter().write(mapper.writeValueAsString(enrolleeService.getEnrolleeByPassport(passport)));
        }
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(enrolleeService.getAllEnrollee()));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String passport = mapper.readTree(requestBody).get("passport").asText();
        enrolleeService.deleteEnrolleeByPassport(passport);
        resp.getWriter().write(mapper.writeValueAsString(enrolleeService.getAllEnrollee()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String oldLinePassport = req.getHeader("Passport");
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Enrollee enrollee = mapper.readValue(requestBody, Enrollee.class);
        enrolleeService.updateEnrollee(enrollee.getFirstName(), enrollee.getLastName(), enrollee.getCertificateScore(), enrollee.getPassport(), oldLinePassport);
        resp.getWriter().write(mapper.writeValueAsString(enrolleeService.getAllEnrollee()));
    }

    @Override
    public void init() throws ServletException {
        super.init();
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        dbConnectionPool.ConnectionPool();
        DAOFactory daoFactory;
        String databaseType = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_TYPE");

        if (databaseType.equals("mysql")) {
            daoFactory = new MySqlDAOFactory();
        } else {
            daoFactory = new SqliteDAOFactory();
        }
        enrolleeService = new EnrolleeService(daoFactory);
    }
}

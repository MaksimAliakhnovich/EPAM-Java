package edu.epam.selectioncommittee.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.dao.factories.MySqlDAOFactory;
import edu.epam.selectioncommittee.dao.factories.SqliteDAOFactory;
import edu.epam.selectioncommittee.entity.Register;
import edu.epam.selectioncommittee.service.RegisterService;
import edu.epam.selectioncommittee.utils.ConfigurationManager;
import edu.epam.selectioncommittee.utils.DBConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by mascon on 28.11.2018.
 */
public class RegisterServlet extends HttpServlet {
    private RegisterService registerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Register register = mapper.readValue(requestBody, Register.class);
        registerService.addLine(register.getEnrolleePassport(), register.getSubjectId(), register.getScore(), register.getFacultyId());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String passport = mapper.readTree(requestBody).get("passport").asText();
        registerService.deleteFromRegister(passport);
//        resp.getWriter().write(mapper.writeValueAsString(registerService.getAllEnrollee()));
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
        registerService = new RegisterService(daoFactory);
    }
}

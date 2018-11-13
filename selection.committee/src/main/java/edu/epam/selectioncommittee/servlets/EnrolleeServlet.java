package edu.epam.selectioncommittee.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.epam.selectioncommittee.dao.EnrolleeDAO;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.dao.factories.MySqlDAOFactory;
import edu.epam.selectioncommittee.dao.factories.SqliteDAOFactory;
import edu.epam.selectioncommittee.entity.Enrollee;
import edu.epam.selectioncommittee.utils.ConfigurationManager;

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
    EnrolleeDAO enrolleeDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Enrollee enrollee = mapper.readValue(requestBody, Enrollee.class);

//        int add = enrolleeDAO.add(firstName, lastName, certificateScore, passport);
//        if (add == 1) {
//            enrolleeId = enrolleeDAO.getByPassport(passport);
//            System.out.println(manager.getString("yourDataIsListed"));
//        } else {
//            System.out.println(manager.getString("yourDataIsNotListed"));
//        }
        resp.getWriter().write(mapper.writeValueAsString(enrollee));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        DAOFactory daoFactory;
        String databaseType = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_TYPE");

        if (databaseType.equals("mysql")) {
            daoFactory = new MySqlDAOFactory();
        } else {
            daoFactory = new SqliteDAOFactory();
        }
        enrolleeDAO = daoFactory.createEnrolleeDAO();
    }
}

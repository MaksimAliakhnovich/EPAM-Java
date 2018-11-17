package edu.epam.selectioncommittee.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.dao.factories.MySqlDAOFactory;
import edu.epam.selectioncommittee.dao.factories.SqliteDAOFactory;
import edu.epam.selectioncommittee.service.FacultyService;
import edu.epam.selectioncommittee.utils.ConfigurationManager;
import edu.epam.selectioncommittee.utils.DBConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mascon on 07.11.2018.
 */

public class FacultyServlet extends HttpServlet {
    private FacultyService facultyService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(facultyService.getAllFac()));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        DAOFactory daoFactory;
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        dbConnectionPool.ConnectionPool();
        String databaseType = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_TYPE");

        if (databaseType.equals("mysql")) {
            daoFactory = new MySqlDAOFactory();
        } else {
            daoFactory = new SqliteDAOFactory();
        }

        facultyService = new FacultyService(daoFactory);
    }
}

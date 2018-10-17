package main.java.edu.epam.selectioncommittee.app;

import main.java.edu.epam.selectioncommittee.dao.factories.DAOFactory;
import main.java.edu.epam.selectioncommittee.dao.factories.MySqlDAOFactory;
import main.java.edu.epam.selectioncommittee.dao.factories.SqliteDAOFactory;
import main.java.edu.epam.selectioncommittee.service.ConnectionService;
import main.java.edu.epam.selectioncommittee.service.LogicService;
import main.java.edu.epam.selectioncommittee.utils.ConfigProperties;

/**
 * Created by mascon on 13.10.2018.
 */
public class SelectCommittee {
    private static LogicService configureLogicService() {
        LogicService logicService;
        DAOFactory daoFactory;
        String databaseType = ConfigProperties.getProperty("DB_TYPE");
        ConnectionService.getInstance().setDatabaseType(databaseType);

        if (databaseType.equals("mysql")) {
            daoFactory = new MySqlDAOFactory();
            logicService = new LogicService(daoFactory);
        } else {
            daoFactory = new SqliteDAOFactory();
            logicService = new LogicService(daoFactory);
        }
        return logicService;
    }

    public void start() {
        LogicService logicService = configureLogicService();

        logicService.print(logicService.getAllFac());
        logicService.print(logicService.getAllSubNameByFacId(1L));

        logicService.addEnrollee("Максим", "Олегович", 10, "HB0000001");
        logicService.getSubScore(1L,10,20,30);
        logicService.addRegLine();

        logicService.addEnrollee("Сергей", "Сергеевич", 20, "HB0000002");
        logicService.getSubScore(1L,40,50,60);
        logicService.addRegLine();

        logicService.addEnrollee("Кирилл", "Денисович", 30, "HB0000003");
        logicService.getSubScore(1L,70,80,90);
        logicService.addRegLine();

        logicService.print(logicService.getStudentByFacId(1L));
    }
}


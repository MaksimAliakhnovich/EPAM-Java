package main.java.edu.epam.selectioncommittee.app;

import main.java.edu.epam.selectioncommittee.dao.factories.DAOFactory;
import main.java.edu.epam.selectioncommittee.dao.factories.MySqlDAOFactory;
import main.java.edu.epam.selectioncommittee.dao.factories.SqliteDAOFactory;
import main.java.edu.epam.selectioncommittee.service.LogicService;
import main.java.edu.epam.selectioncommittee.utils.ConfigProperties;
import main.java.edu.epam.selectioncommittee.utils.DBConnectionPool;

/**
 * Created by mascon on 13.10.2018.
 */
public class SelectCommittee {
    // определяем тип базы данных из property и указываем нужную БД
    private static LogicService configureLogicService() {
        LogicService logicService;
        DAOFactory daoFactory;
        String databaseType = ConfigProperties.getProperty("DB_TYPE");

        if (databaseType.equals("mysql")) {
            daoFactory = new MySqlDAOFactory();
            logicService = new LogicService(daoFactory);
        } else {
            daoFactory = new SqliteDAOFactory();
            logicService = new LogicService(daoFactory);
        }
        return logicService;
    }

    // запуск приложения
    public void start() {
        LogicService logicService = configureLogicService();
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        dbConnectionPool.ConnectionPool();

        logicService.print(logicService.getAllFac()); // выводим список доступных факультетов
        logicService.print(logicService.getAllSubNameByFacId(1L)); // выводим список предметов выбранного факультета

        // вводим фио абитуриента и аттестат, записываем в БД
        logicService.addEnrollee("Максим", "Олегович", 10, "HB0000001");

        // вводим номер факултета и баллы по предметам факультета, записываем в БД
        logicService.getSubScore(1L, 10, 20, 30);
        logicService.addRegLine();

        logicService.addEnrollee("Сергей", "Сергеевич", 20, "HB0000002");
        logicService.getSubScore(1L, 40, 50, 60);
        logicService.addRegLine();

        logicService.addEnrollee("Кирилл", "Денисович", 30, "HB0000003");
        logicService.getSubScore(1L, 70, 80, 90);
        logicService.addRegLine();

        // вводим номер факультета, получаем список абитуриентов с максимальными баллами по факультету.
        // кол-во студентов ограничено планом набора из БД
        logicService.print(logicService.getStudentByFacId(1L));
    }
}


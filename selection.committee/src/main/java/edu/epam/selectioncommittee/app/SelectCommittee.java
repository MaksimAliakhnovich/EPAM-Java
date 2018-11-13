package edu.epam.selectioncommittee.app;

import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.dao.factories.MySqlDAOFactory;
import edu.epam.selectioncommittee.dao.factories.SqliteDAOFactory;
import edu.epam.selectioncommittee.service.LogicService;
import edu.epam.selectioncommittee.utils.ConfigurationManager;
import edu.epam.selectioncommittee.utils.DBConnectionPool;
import edu.epam.selectioncommittee.utils.LocaleManager;

import java.util.Locale;

/**
 * Created by mascon on 13.10.2018.
 */
public class SelectCommittee {
    // определяем тип базы данных из property и указываем нужную БД
    private static LogicService configureLogicService() {
        LogicService logicService;
        DAOFactory daoFactory;
        String databaseType = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_TYPE");

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

        LocaleManager manager = LocaleManager.INSTANCE; // устанавливаем локализацию по умолчанию
        System.out.println(manager.getInstance().getString("myLanguage")); // спрашиваем про язык, на дефолтной локаизации
        manager.changeResource(new Locale("en", "US")); // точно устанавливаем язык и страну, например Англ.

        System.out.println(manager.getInstance().getString("welcome"));
        System.out.println(manager.getInstance().getString("listAllFaculties"));
        logicService.print(logicService.getAllFac()); // выводим список доступных факультетов
        System.out.println(manager.getInstance().getString("enterFacultyNumber"));
        logicService.print(logicService.getAllSubNameByFacId(1L)); // выводим список предметов выбранного факультета
        System.out.println(manager.getInstance().getString("listOfFaculty"));

        // вводим фио абитуриента и аттестат, записываем в БД
        System.out.println(manager.getInstance().getString("enterEnrolleeInfo"));
        logicService.addEnrollee("Максим", "Олегович", 10, "HB0000001");

        // вводим номер факультета и баллы по предметам факультета, записываем в БД
        System.out.println(manager.getInstance().getString("registerEnrolle"));
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
        System.out.println(manager.getInstance().getString("listStudents"));
        logicService.print(logicService.getStudentByFacId(1L));
    }
}


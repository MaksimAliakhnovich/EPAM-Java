package main.java.edu.epam.selectioncommittee.app;

import main.java.edu.epam.selectioncommittee.service.ConnectionService;
import main.java.edu.epam.selectioncommittee.service.LogicService;
import main.java.edu.epam.selectioncommittee.utils.ReadProperties;

/**
 * Created by mascon on 13.10.2018.
 */
public class SelectCommittee {
    public void start() {
        ReadProperties props = new ReadProperties();
        String databaseType = props.getAllProperties().getProperty("DB_TYPE");
        ConnectionService.getInstance().setDatabaseType(databaseType);

        LogicService logicService = new LogicService();
        logicService.print(logicService.getAllFac());
        logicService.print(logicService.getAllSubNameCurFac(1L));

        logicService.addEnrollee("Сергей", "Сергеевич", 90);
        logicService.getSubScore(1L,20,30,40);
        logicService.addRegLine();
        logicService.print(logicService.getStudentByFacId(1L));
    }
}


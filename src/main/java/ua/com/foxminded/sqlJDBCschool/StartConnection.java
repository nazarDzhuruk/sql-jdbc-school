package ua.com.foxminded.sqlJDBCschool;

import ua.com.foxminded.sqlJDBCschool.configurations.DBConnectionConfigs;
import ua.com.foxminded.sqlJDBCschool.createData.GroupCreateTable;
import ua.com.foxminded.sqlJDBCschool.createData.StudentCreateTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StartConnection extends DBConnectionConfigs {
    Connection database;

    public Connection connectToEdit() throws SQLException {
        String connectTo = "jdbc:postgresql://" + localhost + ":" + port + "/" + dbName;

        database = DriverManager.getConnection(connectTo, user, password);
        return database;
    }

    public Connection createDatabase() throws SQLException, IOException {
        createFakeDate(fakeDataInsert());
        return database;
    }

    private Connection fakeDataInsert() throws SQLException, IOException {
        String connectTo = "jdbc:postgresql://" + localhost + ":" + port + "/" + dbName;

        String createTables = new String(Files.readAllBytes(Path.of("src/main/resources/database/dbScript.sql")));

        database = DriverManager.getConnection(connectTo, user, password);
        Statement script = database.createStatement();
        script.execute(createTables);
        return database;
    }

    private void createFakeDate(Connection connection) throws SQLException {
        GroupCreateTable groups = new GroupCreateTable();
        groups.fillGroupsTable(connection);
        StudentCreateTable students = new StudentCreateTable();
        students.fillStudentTable(connection);
    }
}

package ua.com.foxminded.sqlJDBCschool.createData;

import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;

import java.sql.*;

public class StudentCreateTable {
    private String comma = ",";
    private String scriptSQL = "INSERT INTO " + DBTableConsts.STUDENTS +"("
        + DBTableConsts.FIRST_NAME + comma + DBTableConsts.LAST_NAME + comma + DBTableConsts.STUDENT_ID
            + comma + DBTableConsts.GROUP_ID
        + ") VALUES (?, ?, ?, ?)";

    public void fillStudentTable(Connection connection) throws SQLException {
        StudentsGenerator create = new StudentsGenerator();
        PreparedStatement statement = connection.prepareStatement(scriptSQL);
        create.student().forEach(student -> {
            try {
                statement.setString(1, student.getName());
                statement.setString(2, student.getSurname());
                statement.setInt(3, student.getStudentID());
                statement.setInt(4, student.getGroupID());
                statement.addBatch();
            } catch (SQLException possibleException) {
                possibleException.printStackTrace();
            }
        });
        statement.executeBatch();
    }
}
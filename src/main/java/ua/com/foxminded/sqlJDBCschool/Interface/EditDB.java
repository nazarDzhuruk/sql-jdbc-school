package ua.com.foxminded.sqlJDBCschool.Interface;

import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;
import ua.com.foxminded.sqlJDBCschool.StartConnection;
import ua.com.foxminded.sqlJDBCschool.createData.StudentsToCoursesGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditDB extends StartConnection {

    public void assigner() throws SQLException {
        StudentsToCoursesGenerator studentsToCourses = new StudentsToCoursesGenerator();
        String insertion = "INSERT INTO " + DBTableConsts.STUDENT_COURSES + "(" + DBTableConsts.STUDENT_ID + "," +
                DBTableConsts.COURSE +
                ") VALUES (?, ?)";
        PreparedStatement statement = connectToEdit().prepareStatement(insertion);
        studentsToCourses.courseToStudent().forEach(student -> {
            try {
                statement.setInt(1, Integer.parseInt(student.getStudentID()));
                statement.setString(2, student.getCourses());
                statement.addBatch();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        });
        statement.executeBatch();
    }

    public void deleteFromCourse(String studentID, String courseName) throws SQLException {
        String delete = "DELETE FROM " + DBTableConsts.STUDENT_COURSES + " WHERE " + DBTableConsts.STUDENT_ID + " = ? AND " +
                DBTableConsts.COURSE + "= ?";
        PreparedStatement statement = connectToEdit().prepareStatement(delete);
        try {
            statement.setInt(1, Integer.parseInt(studentID));
            statement.setString(2, courseName);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        statement.executeUpdate();
    }

    public void addStudent(String name, String surname, String studentID, String groupID) throws  SQLException {
        String insertion = "INSERT INTO " + DBTableConsts.STUDENTS + "(" + DBTableConsts.FIRST_NAME + "," +
                DBTableConsts.LAST_NAME +
                "," + DBTableConsts.STUDENT_ID + "," + DBTableConsts.GROUP_ID + ") VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connectToEdit().prepareStatement(insertion);
        try {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, Integer.parseInt(studentID));
            statement.setInt(4, Integer.parseInt(groupID));
            statement.addBatch();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        statement.executeUpdate();
    }

    public void addCourse(String studentID, String course) throws SQLException {
        String insertion = "INSERT INTO " + DBTableConsts.STUDENT_COURSES + "(" + DBTableConsts.STUDENT_ID + "," +
                DBTableConsts.COURSE + ") VALUES (?, ?)";
        PreparedStatement statement = connectToEdit().prepareStatement(insertion);
        try {
            statement.setInt(1, Integer.parseInt(studentID));
            statement.setString(2, course);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        statement.executeUpdate();
    }

    public void deleteStudent(String studentID) throws SQLException {
        String delete = "DELETE FROM " + DBTableConsts.STUDENTS + " WHERE " + DBTableConsts.STUDENT_ID + "= ?";
        PreparedStatement statement = connectToEdit().prepareStatement(delete);
        try {
            statement.setInt(1, Integer.parseInt(studentID));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        statement.executeUpdate();
    }
}


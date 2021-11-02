package ua.com.foxminded.sqlJDBCschool.createData;

import ua.com.foxminded.sqlJDBCschool.StartConnection;

import java.util.List;

public class CoursesStudentsConstructor extends StartConnection {
    private String studentID;
    private String courses;

    public String getStudentID() {
        return studentID;
    }

    public String getCourses() {
        return courses;
    }

    public CoursesStudentsConstructor(String studentID, String courses) {
        this.studentID = studentID;
        this.courses = courses;
    }
}

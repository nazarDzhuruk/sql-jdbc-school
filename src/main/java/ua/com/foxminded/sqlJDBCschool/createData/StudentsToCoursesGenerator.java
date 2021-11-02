package ua.com.foxminded.sqlJDBCschool.createData;

import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;
import ua.com.foxminded.sqlJDBCschool.StartConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentsToCoursesGenerator extends StartConnection {

    public List<CoursesStudentsConstructor> courseToStudent() throws SQLException {
        List<String> studentsRelation = studentsRelation();
        List<String> coursesRelation = coursesRelation();
        Collections.shuffle(coursesRelation);
        return IntStream.range(0, 200).mapToObj(i -> new CoursesStudentsConstructor(studentsRelation.get(i), coursesRelation.get(i)))
                .collect(Collectors.toList());
    }

    private List<String> coursesRelation() throws SQLException {
        List<String> course = courses();
        int nTimes = studentsRelation().size() / courses().size();
        return course.stream().map(courses -> Collections.nCopies(nTimes, courses))
                .flatMap(List::stream).collect(Collectors.toList());
    }


    private List<String> studentsRelation() throws SQLException {
        List<String> IDs = studentsID();
        Random random = new Random();
        int min = 1;
        int max = 3;
        int nTimes = random.nextInt((max - min) + 1) + min;
        return IDs.stream().map(s -> Collections.nCopies(nTimes, IDs
                .get(random.nextInt(IDs.size())))).flatMap(List::stream).sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    private List<String> studentsID() throws SQLException {
        String studentsIDs = "SELECT * FROM " + DBTableConsts.STUDENTS;
        PreparedStatement statement = connectToEdit().prepareStatement(studentsIDs);
        ResultSet result = statement.executeQuery();
        List<String> student_id = new ArrayList<>();
        while (result.next()) {
            student_id.add(result.getString(DBTableConsts.STUDENT_ID));
        }
        return student_id;
    }

    private List<String> courses() throws SQLException {
        String coursesNames = "SELECT * FROM " + DBTableConsts.COURSES;
        PreparedStatement statement = connectToEdit().prepareStatement(coursesNames);
        ResultSet result = statement.executeQuery();
        List<String> courses = new ArrayList<>();
        while (result.next()) {
            courses.add(result.getString(DBTableConsts.COURSE_NAME));
        }
        return courses;
    }
}

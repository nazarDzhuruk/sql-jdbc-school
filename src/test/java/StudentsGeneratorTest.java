import org.junit.jupiter.api.Test;
import ua.com.foxminded.sqlJDBCschool.createData.StudentConstructor;
import ua.com.foxminded.sqlJDBCschool.createData.StudentsGenerator;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class StudentsGeneratorTest {
    @Test
    public void studentRandomNamesTest() throws SQLException {
        StudentsGenerator studentsGenerator = new StudentsGenerator();
        List<String> names = studentsGenerator.student().stream().map(StudentConstructor::getName)
                .collect(Collectors.toList());
        List<String> randomNames = studentsGenerator.student().stream().map(StudentConstructor::getName)
                .collect(Collectors.toList());
        assertNotSame(names, randomNames);
        assertThat(names, hasSize(200));
    }
    @Test
    public void studentRandomSurnamesTest() throws SQLException {
        StudentsGenerator studentsGenerator = new StudentsGenerator();
        List<String> surnames = studentsGenerator.student().stream().map(StudentConstructor::getSurname)
                .collect(Collectors.toList());
        List<String> randomSurnames = studentsGenerator.student().stream().map(StudentConstructor::getSurname)
                .collect(Collectors.toList());
        assertNotSame(surnames, randomSurnames);
        assertThat(surnames, hasSize(200));
    }
    @Test
    public void studentIDTest() throws SQLException {
        StudentsGenerator studentsGenerator = new StudentsGenerator();
        List<Integer> studentsID = studentsGenerator.student().stream().map(StudentConstructor::getStudentID)
                .collect(Collectors.toList());
        List<Integer> randomStudentsID = studentsGenerator.student().stream().map(StudentConstructor::getStudentID)
                .collect(Collectors.toList());
        assertNotSame(studentsID, randomStudentsID);
        assertThat(studentsID, hasSize(200));
    }
    @Test
    public void studentGroupTest() throws SQLException {
        StudentsGenerator studentsGenerator = new StudentsGenerator();
        List<Integer> groups = studentsGenerator.student().stream().map(StudentConstructor::getGroupID)
                .collect(Collectors.toList());
        assertThat(groups, hasSize(200));
    }
}
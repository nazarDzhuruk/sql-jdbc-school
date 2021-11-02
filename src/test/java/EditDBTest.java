import org.junit.jupiter.api.Test;
import ua.com.foxminded.sqlJDBCschool.Interface.EditDB;
import ua.com.foxminded.sqlJDBCschool.StartConnection;
import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class EditDBTest extends StartConnection {
    @Test
    public void addStudentTest() throws SQLException {
        assertThat(addStudent(), hasItem("2345"));
    }

    public List<String> addStudent() throws SQLException {
        String showTable = "SELECT * FROM " + DBTableConsts.STUDENTS;
        EditDB editDB = new EditDB();
        editDB.deleteStudent("2345");
        editDB.addStudent("TestName", "TestSurname", "2345", "321");
        PreparedStatement statement = connectToEdit().prepareStatement(showTable);
        ResultSet resultSet = statement.executeQuery();
        List<String> addStudent = new ArrayList<>();
        while (resultSet.next()) {
            addStudent.add(resultSet.getString(DBTableConsts.STUDENT_ID));
        }
        return addStudent;
    }

    @Test
    public void deleteStudentByIDTest() throws SQLException {
        assertThat(deletedTestStudent(), not(hasItem("1234")));
    }

    public List<String> deletedTestStudent() throws SQLException {
        String showTable = "SELECT * FROM " + DBTableConsts.STUDENTS;
        EditDB editDB = new EditDB();
        editDB.addStudent("Test", "Test", "1234", "321");
        editDB.deleteStudent("1234");
        PreparedStatement statement = connectToEdit().prepareStatement(showTable);
        ResultSet resultSet = statement.executeQuery();
        List<String> deleteStudent = new ArrayList<>();
        while (resultSet.next()) {
            deleteStudent.add(resultSet.getString(DBTableConsts.STUDENT_ID));
        }
        return deleteStudent;
    }

}

import org.junit.jupiter.api.Test;
import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;
import ua.com.foxminded.sqlJDBCschool.StartConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class StartConnectionTest {
    @Test
    public void serverStudentTableTest() throws SQLException {
        assertThat(serverStudentNames(), hasSize(201));
    }
    public List<String> serverStudentNames() throws SQLException {
        String showTable = "SELECT * FROM " + DBTableConsts.STUDENTS;
        StartConnection connection = new StartConnection();
        PreparedStatement statement = connection.connectToEdit().prepareStatement(showTable);
        ResultSet result = statement.executeQuery();
        List<String> names = new ArrayList<>();
        while (result.next()) {
            names.add(result.getString(DBTableConsts.FIRST_NAME));
        }
        return names;
    }
}

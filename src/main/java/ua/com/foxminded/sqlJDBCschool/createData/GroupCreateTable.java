package ua.com.foxminded.sqlJDBCschool.createData;

import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupCreateTable {
    private String comma = ",";
    private String scriptSQL = "INSERT INTO " + DBTableConsts.GROUPS + "(" + DBTableConsts.GROUP_ID +
            comma + DBTableConsts.GROUP_NAME + ") VALUES (?, ?)";

    public void fillGroupsTable(Connection connection) throws SQLException {
        GroupsGenerator create = new GroupsGenerator();
        PreparedStatement statement = connection.prepareStatement(scriptSQL);
        create.group().forEach(group -> {
            try {
                statement.setInt(1, group.getGroupID());
                statement.setString(2, group.getGroupName());
                statement.addBatch();

            } catch (SQLException possibleException) {
                possibleException.printStackTrace();
            }
        });
        statement.executeBatch();
    }
}

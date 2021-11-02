package ua.com.foxminded.sqlJDBCschool.Interface;

import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;
import ua.com.foxminded.sqlJDBCschool.StartConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Interface extends StartConnection {
    private String PLUS = "+";
    private String DASH = "-";
    private String SPACE = " ";
    private String VLINE = "|";
    private String DOT = ".";
    private String QUOTES = "'";

    public List<String> findGroups() throws IOException, SQLException {
        String find = new String(Files.readAllBytes(Path.of("src/main/resources/database/findGroup.sql")));
        Statement statement = connectToEdit().createStatement();
        ResultSet resultSet = statement.executeQuery(find);
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(DBTableConsts.GROUP_ID));
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    public List<String> prettyTable() throws SQLException {
        List<String> table = studentTable();
        String addDashes = adjustableString(DASH, largestString(table));
        String dashes = PLUS + addDashes + PLUS;
        table.add(0, dashes);
        return table;
    }

    public List<String> findByCourse(String courseName) throws IOException, SQLException {
        String sqlQuery = new String(Files.readAllBytes(Path.of("src/main/resources/database/relatedCourse.sql")));
        String find = sqlQuery + QUOTES + courseName + QUOTES;
        PreparedStatement statement = connectToEdit().prepareStatement(find);
        List<String> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            list.add(resultSet.getString(DBTableConsts.STUDENT_ID));
        }
        return list;
    }

    private List<String> studentTable() throws SQLException {
        List<String> names = formatNames();
        List<String> surnames = formatSurnames();
        List<String> ID = studentsID();
        return IntStream.range(0, names.size())
                .mapToObj(i -> VLINE + SPACE + (i + 1) + DOT + SPACE + VLINE + names.get(i) + VLINE +
                        SPACE + surnames.get(i) +
                        VLINE + ID.get(i))
                .collect(Collectors.toList());
    }

    private List<String> formatNames() throws SQLException {
        List<String> names = names();
        int maxLen = names.stream().mapToInt(String::length).max().getAsInt();
        return names.stream()
                .map(car -> String.format("%-" + maxLen + "s", car))
                .collect(Collectors.toList());
    }

    private List<String> formatSurnames() throws SQLException {
        List<String> surnames = surnames();
        int maxLen = surnames.stream().mapToInt(String::length).max().getAsInt();
        return surnames.stream()
                .map(car -> String.format("%-" + maxLen + "s", car))
                .collect(Collectors.toList());
    }


    private List<String> studentsID() throws SQLException {
        String showTable = "SELECT * FROM " + DBTableConsts.STUDENTS;
        PreparedStatement statement = connectToEdit().prepareStatement(showTable);
        ResultSet result = statement.executeQuery();
        List<String> student_id = new ArrayList<>();
        while (result.next()) {
            student_id.add(result.getString(DBTableConsts.STUDENT_ID));
        }
        return student_id;
    }

    private List<String> surnames() throws SQLException {
        String showTable = "SELECT * FROM " + DBTableConsts.STUDENTS;
        PreparedStatement statement = connectToEdit().prepareStatement(showTable);
        ResultSet result = statement.executeQuery();
        List<String> surnames = new ArrayList<>();
        while (result.next()) {
            surnames.add(result.getString(DBTableConsts.LAST_NAME));
        }
        return surnames;
    }

    private List<String> names() throws SQLException {
        String showTable = "SELECT * FROM " + DBTableConsts.STUDENTS;
        PreparedStatement statement = connectToEdit().prepareStatement(showTable);
        ResultSet result = statement.executeQuery();
        List<String> names = new ArrayList<>();
        while (result.next()) {
            names.add(result.getString(DBTableConsts.FIRST_NAME));
        }
        return names;
    }

    private String adjustableString(String format, int number) {
        return IntStream.range(0, number).mapToObj(i -> format).collect(Collectors.joining());
    }

    private int largestString(List<String> table) {
        Optional<String> longest;
        longest = table.stream().min((e1, e2) -> e1.length() > e2.length() ? -1 : 1);
        return longest.get().length();
    }
}

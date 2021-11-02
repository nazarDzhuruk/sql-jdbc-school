package ua.com.foxminded.sqlJDBCschool.createData;

import com.github.javafaker.Faker;
import ua.com.foxminded.sqlJDBCschool.configurations.DBTableConsts;
import ua.com.foxminded.sqlJDBCschool.StartConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentsGenerator extends StartConnection{
    private String query = "SELECT " + DBTableConsts.GROUP_ID + " FROM " + DBTableConsts.GROUPS;

    public List<StudentConstructor> student() throws SQLException {
        List<String> name = generatedNames();
        List<String> surname = generatedSurname();
        List<Integer> id = studentID();
        List<Integer> groupID = setID(groupID());
        Collections.shuffle(groupID);
        return IntStream.range(0, name.size()).mapToObj(i -> new StudentConstructor(name.get(i), surname.get(i), id.get(i), groupID.get(i)))
                .collect(Collectors.toList());
    }

    private List<String> generatedNames() {
        Faker names = new Faker();
        return IntStream.range(0, 200).mapToObj(i -> names.name().firstName())
                .collect(Collectors.toList());
    }

    private List<String> generatedSurname() {
        Faker surnames = new Faker();
        return IntStream.range(0, 200).mapToObj(i -> surnames.name().lastName())
                .collect(Collectors.toList());
    }
    private List<Integer> setID(List<Integer> groupID) {
        List<String> names = generatedNames();
        int nTimes = names.size() / groupID.size();
        return groupID.stream().map(group -> Collections.nCopies(nTimes, group))
                .flatMap(List::stream).collect(Collectors.toList());
    }

    private List<Integer> groupID() throws SQLException {
        PreparedStatement preparedStatement = connectToEdit().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Integer> id = new ArrayList<>();
        while(resultSet.next()){
            id.add(resultSet.getInt(DBTableConsts.GROUP_ID));
        }return  id;
    }

    private List<Integer> studentID() {
        Random random = new Random();
        return IntStream.range(0, 200).mapToObj(i -> random.nextInt(99999))
                .collect(Collectors.toList());
    }
}

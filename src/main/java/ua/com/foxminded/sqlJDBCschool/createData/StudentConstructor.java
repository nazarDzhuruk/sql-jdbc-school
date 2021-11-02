package ua.com.foxminded.sqlJDBCschool.createData;

public class StudentConstructor {
    private String name;
    private String surname;
    private Integer studentID;
    private Integer groupID;
    public StudentConstructor(String name, String surname, Integer studentID, Integer groupID) {
        this.name = name;
        this.surname = surname;
        this.studentID = studentID;
        this.groupID = groupID;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public Integer getGroupID() {
        return groupID;
    }
}

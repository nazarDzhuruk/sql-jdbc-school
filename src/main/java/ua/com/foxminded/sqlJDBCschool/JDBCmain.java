package ua.com.foxminded.sqlJDBCschool;

import ua.com.foxminded.sqlJDBCschool.Interface.EditDB;
import ua.com.foxminded.sqlJDBCschool.Interface.Interface;

import java.util.Scanner;

public class JDBCmain {
    public static void main(String[] args) throws Exception {

        StartConnection connection = new StartConnection();
        connection.createDatabase();
        EditDB edit = new EditDB();
        Interface i = new Interface();
        edit.assigner();

        System.out.println("-Tables has been created-");
        Scanner scanner = new Scanner(System.in);

        System.out.println("To show table enter - 1");
        System.out.println("To delete student enter - 2");
        System.out.println("To add student enter - 3");
        System.out.println("To find groups enter - 4");
        System.out.println("To find all students related to course enter - 5");
        System.out.println("To add student to the course enter - 6");
        System.out.println("To delete student from the course enter - 7");

        String option = scanner.nextLine();
        switch (Integer.parseInt(option)) {
            case 1:
                i.prettyTable().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Enter student ID");
                String id = scanner.nextLine();
                edit.deleteStudent(id);
                break;
            case 3:
                System.out.print("Provide name: ");
                String name = scanner.nextLine();
                System.out.print("Provide surname: ");
                String surname = scanner.nextLine();
                System.out.print("Provide student id: ");
                String studentID = scanner.nextLine();
                System.out.print("Provide group: ");
                String group = scanner.nextLine();
                edit.addStudent(name, surname, studentID, group);
                break;
            case 4:
                i.findGroups().forEach(System.out::println);
                break;
            case 5:
                System.out.print("Enter course name: ");
                String course = scanner.nextLine();
                System.out.println(i.findByCourse(course));
                break;
            case 6:
                System.out.print("studentID: ");
                String student_ID = scanner.nextLine();
                System.out.print("course name: ");
                String courseName = scanner.nextLine();
                edit.addCourse(student_ID, courseName);
                break;
            case 7:
                System.out.print("studentID: ");
                String IDstudent = scanner.nextLine();
                System.out.print("course name: ");
                String nameCourse = scanner.nextLine();
                edit.deleteFromCourse(IDstudent, nameCourse);
                break;
        }
    }
}

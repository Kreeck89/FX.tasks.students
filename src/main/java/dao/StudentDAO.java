package dao;

import model.Student;

public interface StudentDAO {

    void registrNewStudent(Student student);

    boolean checkDoubleEmail(String email);

    String getStudentRole(String log, String pas);
}
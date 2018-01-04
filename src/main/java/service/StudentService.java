package service;

import model.Student;

public interface StudentService {

    void registryNewStudent(Student student);

    boolean checkDoubleEmail(String email);

    String getStudentRole(String log, String pas);
}
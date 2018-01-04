package service.impl;

import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import model.Student;
import service.StudentService;

public class StudentServiceImpl implements StudentService {

    @Override
    public void registryNewStudent(Student student) {
        StudentDAO studentDAO= new StudentDAOImpl();
        studentDAO.registrNewStudent(student);
    }

    @Override
    public boolean checkDoubleEmail(String email) {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.checkDoubleEmail(email);
    }

    @Override
    public String getStudentRole(String log, String pas) {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.getStudentRole(log, pas);
    }
}
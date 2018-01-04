package demo;

import model.Task;
import service.StudentService;
import service.TaskService;
import service.impl.StudentServiceImpl;
import service.impl.TaskServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        TaskService taskService = new TaskServiceImpl();
//        Task task = new Task();
//        task.setStudentID(2);
//        task.setTask("Task");
//        task.setAnswer("Answer");
//        try {
//            taskService.save(task);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Task task = new Task();
//        task.setId(3);
        task.setStudentID(4);
        task.setTask("Task from Demo");
        task.setAnswer("Don`t have");

        try {
            taskService.save(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        List<Task> list = taskService.loadAllTasks();
//        for (Task elem : list) {
//            System.out.println(elem);
//        }
//
//
//        StudentService service = new StudentServiceImpl();
//        String qwerty = service.getStudentRole("alex@gmail.com", "qwerty");
//        System.out.println(qwerty);
    }
}

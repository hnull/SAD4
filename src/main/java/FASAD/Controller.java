package FASAD;

import Model.*;
import Service.DB;
import Service.RunnableSend;

import java.util.ArrayList;
import java.util.List;

public class Controller implements FASAD {
    public void login(String username, String password) {
        if(DB.currnetUser.isLogin()) {
            System.out.println("User " + DB.currnetUser.getUserName() + " was already logged in");
            return;
        }
        else if(DB.currnetUser.getUserName().equals(username) && DB.currnetUser.getPassword().equals(password)) {
            DB.currnetUser.setLogin(true);
            System.out.println("User " + DB.currnetUser.getUserName() + " logged in succesfully");
            DB.lifeCycle = LifeCycle.loggedIn;
            return;
        }
        else {
            System.out.println("username or password is incorrect");
        }
    }

    public void getExams() {
        if(DB.lifeCycle == LifeCycle.idle) {
            System.out.println("please log in first");
            return;
        }
        for(Presentation ps: DB.presentations) {
            System.out.println("-----------------------------------------");
            System.out.print("exam Id : ");
            System.out.println(ps.exam.getExamId());
            System.out.print("room number : ");
            System.out.println(ps.exam.classRoom.classNumber);
            System.out.print("course name : ");
            System.out.println(ps.course.getCoueseName());
            System.out.print("start time : ");
            System.out.println(ps.exam.startAt);
            System.out.print("end at : ");
            System.out.println(ps.exam.endAt);
        }
        System.out.println("-----------------------------------------");
    }

    public void selectExam(int id) {
        if(DB.lifeCycle == LifeCycle.idle) {
            System.out.println("please log in first");
            return;
        }
        if(!DB.setCurrentExam(id)) {
            System.out.println("can not find exam with id : " + id);
            return;
        }
        if(DB.currentExam.signedByProfessor == true) {
            System.out.println("exam with id " + DB.currentExam.getExamId() + " has been finished");
            DB.lifeCycle = LifeCycle.loggedIn;
            DB.currentExam = null;
            return;
        }
        DB.lifeCycle = LifeCycle.examSelected;
    }

    public void selectStudent(int id) {
        if(DB.lifeCycle != LifeCycle.examSelected) {
            System.out.println("please select exam first");
            return;
        }
        List<StudentCourse> students = DB.currentExam.getStudens();
        for(StudentCourse student: students) {
            if(student.getStudent().getId() == id) {
                System.out.println("student with id " + student.getStudent().getId() + " presence set");
                System.out.print("firstname : " + student.getStudent().getFirstName());
                System.out.println(" lastname : " + student.getStudent().getLastName());
                student.setPresent(true);
                return;
            }
        }
        System.out.println("can not find student in this exam");
    }

    public boolean signByProfessor(int id) {
        if(DB.lifeCycle != LifeCycle.examFinished) {
            System.out.println("please select exam first");
            return false;
        }
        Professor prof = DB.currentExam.findProfessor();
        if(prof.getId() != id) {
            System.out.println("prof id not match with prof's id of class");
            return false;
        }
        DB.currentExam.signedByProfessor = true;
        DB.lifeCycle = LifeCycle.signByProfessor;
        return true;
    }

    public void addStudentManuall(int studentId, int chairNum) {
        if(DB.lifeCycle != LifeCycle.examSelected) {
            System.out.println("please select exam first");
            return;
        }
        Student student = new Student();
        student.setId(studentId);
        StudentCourse studentCourse = new StudentCourse(chairNum);
        studentCourse.setPresent(true);
        studentCourse.setStudent(student);
        DB.currentExam.getStudens().add(studentCourse);
    }

    public void sendExamPresetationData() {
        List<Integer> presentStudId = new ArrayList<>();
        for(StudentCourse student : DB.currentExam.getStudens()) {
            if(student.isPresent())
                presentStudId.add(student.getStudent().getId());
        }
        StringBuilder data = new StringBuilder("{ \"exam_id\": " + DB.currentExam.getExamId() + " ," +
                "\"is_teacher_signed\": " + (DB.currentExam.signedByProfessor ? "\"true\"": "\"false\"") + " ," +
                "\"present_students_list\": [");
        for (Integer sid :
                presentStudId) {
            data.append(" ").append(sid).append(",");
        }
        data.append("]}");
        System.out.println("json sent to server is : " + data);
        RunnableSend myRunnable = new RunnableSend(data);
        Thread t = new Thread(myRunnable);
        t.start();
        DB.lifeCycle = LifeCycle.loggedIn;
    }
}

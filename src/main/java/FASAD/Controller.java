package FASAD;

import Model.*;
import Service.DB;
import org.json.simple.JSONObject;

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
            }
        }
    }

    public void signByProfessor(int id) {
        if(DB.lifeCycle != LifeCycle.examFinished) {
            System.out.println("please select exam first");
            return;
        }
        Professor prof = DB.currentExam.findProfessor();
        if(prof.getId() != id) {
            System.out.println("prof id not match with prof's id of class");
            return;
        }
        DB.currentExam.signedByProfessor = true;
        DB.lifeCycle = LifeCycle.signByProfessor;
    }

    public void sendExamPresetationData() {
        JSONObject jo = new JSONObject();
        List<Integer> presentStudId = new ArrayList<>();
        for(StudentCourse student : DB.currentExam.getStudens()) {
            if(student.isPresent())
                presentStudId.add(student.getStudent().getId());
        }
        jo.put("exam_id", DB.currentExam.getExamId());
        jo.put("is_teacher_signed", DB.currentExam.signedByProfessor);
        jo.put("present_students_list", presentStudId);
        System.out.println(jo);
    }
}

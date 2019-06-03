package FASAD;

import Model.Presentation;
import Service.DB;

public class Controller implements FASAD {
    public void login(String username, String password) {
        if(DB.currnetUser.isLogin()) {
            System.out.println("User " + DB.currnetUser.getUserName() + " was already logged in");
            return;
        }
        else if(DB.currnetUser.getUserName().equals(username) && DB.currnetUser.getPassword().equals(password)) {
            DB.currnetUser.setLogin(true);
            System.out.println("User " + DB.currnetUser.getUserName() + " logged in succesfully");
            return;
        }
        else {
            System.out.println("username or password is incorrect");
        }
    }

    public void getExams() {
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
}

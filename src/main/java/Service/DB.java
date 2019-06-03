package Service;

import Model.*;

import java.util.ArrayList;
import java.util.List;

public class DB {
    public static LifeCycle lifeCycle = LifeCycle.idle;
    public static List<Presentation> presentations = new ArrayList<Presentation>();
    public static List<Student> students = new ArrayList<Student>();
    public static List<Professor> professors = new ArrayList<Professor>();
    public static Invigilator currnetUser = new Invigilator("گیتا", "نصیری", "nasiri", "123");
    public static Exam currentExam = new Exam();

    public static Professor findProfessor(int id) {
        for(Professor p : professors) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static Student findStudent(int id) {
        for(Student s : students) {
            if(s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public static boolean setCurrentExam(int id) {
        for(Presentation ps : presentations) {
            if(ps.exam.getExamId() == id) {
                currentExam = ps.exam;
                System.out.println("exam " + DB.currentExam.getExamId() + " is selected");
                System.out.println("Exam's Students : ");
                ps.showStudentsId();
                return true;
            }
        }
        return false;
    }
}

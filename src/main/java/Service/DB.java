package Service;

import Model.Presentation;
import Model.Professor;
import Model.Student;

import java.util.ArrayList;
import java.util.List;

public class DB {
    public static List<Presentation> presentations = new ArrayList<Presentation>();
    public static List<Student> students = new ArrayList<Student>();
    public static List<Professor> professors = new ArrayList<Professor>();

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
}

package Model;

import Service.DB;

import java.util.List;

public class Presentation {
    private int classNumber;
    private List<StudentCourse> students;
    private Professor professor;
    public Course course;
    public Schedule schedule;
    public Exam exam;

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public List<StudentCourse> getStudents() {
        return students;
    }

    public void setStudents(List<StudentCourse> students) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void showStudentsId() {
        int i = 1;
        System.out.println("-----------------------------------------");
        for(StudentCourse studentCourse : students) {
            System.out.println(i++ + ". id :" + studentCourse.getStudent().getId());
        }
        System.out.println("-----------------------------------------");
    }
}

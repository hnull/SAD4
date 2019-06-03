package Model;

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
}

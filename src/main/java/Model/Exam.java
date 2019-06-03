package Model;

import Service.DB;

import java.util.List;

public class Exam {
    public String Date;
    private int examId;
    public String startAt;
    public String endAt;
    public Invigilator invigilator;
    public ClassRoom classRoom;
    public boolean signedByProfessor;

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public List<StudentCourse> getStudens() {
        for(Presentation ps: DB.presentations) {
            if(ps.exam.getExamId() == this.examId) {
                return ps.getStudents();
            }
        }
        return null;
    }

    public Professor findProfessor() {
        for(Presentation ps: DB.presentations) {
            if(ps.exam.getExamId() == this.examId) {
                return ps.getProfessor();
            }
        }
        return null;
    }
}

package Model;

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
}

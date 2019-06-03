package Model;

public class StudentCourse {
    private int score;
    private Student student;
    private boolean isPresent;
    public int chairNumber;

    public StudentCourse(int chairNumber) {
        isPresent = false;
        this.chairNumber = chairNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}

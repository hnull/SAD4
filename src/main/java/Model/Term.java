package Model;

import java.util.List;

public class Term {
    List<Presentation> presentations;
    private int year;
    private Semester semester;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }


    enum Semester { Fall, Spring};
}

package Model;

import java.sql.Time;

public class Schedule {
    private Days days;
    private Time startTime;
    private Time endTime;

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    enum Days { Saturday, Sunday, Monday, Thuesday, Wednesday, Thursday, Friday};
}


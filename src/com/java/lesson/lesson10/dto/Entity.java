package com.java.lesson.lesson10.dto;

/**
 * Created by User on 02.03.2018.
 */
public class Entity {
    private String firstName;
    private String secondName;
    private int course;
    private String subject;
    private int mark;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return '\n' + " name=" + firstName + " " + secondName +
                ", course=" + course + ", subject=" + subject + ", mark=" + mark;
    }
}

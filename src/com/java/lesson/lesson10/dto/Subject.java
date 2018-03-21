package com.java.lesson.lesson10.dto;

/**
 * Created by User on 20.02.2018.
 */
public class Subject extends Model{
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "\n" + "id=" + id + ", subject='" + subject;
    }
}
